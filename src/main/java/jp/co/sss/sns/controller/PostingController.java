package jp.co.sss.sns.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.form.CommentForm;
import jp.co.sss.sns.form.PostingForm;
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.LikeRepository;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;

@Controller
public class PostingController {
	@Autowired
	PostingRepository postingRepository;
	@Autowired
	HttpSession session;
	@Autowired
	LikeRepository likeRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	
	
	// 投稿記事一覧表示機能
	@RequestMapping("/snssns/findAll")
	public String showList(Model model,@ModelAttribute PostingForm form) {

		List<Posting> posting = postingRepository.findAll();
//		List<Comment> postings = commentRepository.findAll();
		if (!posting.isEmpty()) {
			session.setAttribute("posting", posting);
		} else {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}
		return "index/index";
	}

	// 投稿ページ遷移処理
	@RequestMapping("/snssns/posts")
	public String postsposts(Model model,@ModelAttribute PostingForm form) {
		return "posting/posting_page";
	}

	// 投稿確認画面へ（登録）機能 入力チェックあり
	@RequestMapping("/snssns/posting")
	public String doLogin(Model model ,@Valid @ModelAttribute PostingForm form, BindingResult result) {
		if (result.hasErrors()) {
//			List<String> errorList = new ArrayList<String>();
//			for (ObjectError error : result.getAllErrors()) {
//				errorList.add(error.getDefaultMessage());
//			}
			//別のところのこーど
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList",errorList);
//			model.addAttribute("errMessage", "文字数が多いです");
			return postsposts(model,form);
//			return "redirect:/posting/posting_page";
		}
		String content = form.getContents();
//		List<content> contentList = form.getContents()
//		 List<String> list = new ArrayList<String>(){
//	            {
//	                add(form.getContents());
//	                add("Orange");
//	                add("Melon");
//	            }
//	        };
		content = form.getTitle();

		if (content != null) {
//				List<ItemBean> itemBeanList = BeanCopy.copyEntityToItemBean(itemList.getContent());
			model.addAttribute("contents", content);
			//条件を満たした場合投稿内容確認画面へ
			return "posting/posting_check";

		} else {
//			model.addAttribute("errMessage", "文字数が多い、もしくは何も書かれていません");
			return postsposts(model,form);
//			return "redirect:/posting/posting_page";
		}

	}
	// 投稿するボタン
	@RequestMapping("/snssns/dopost")
	public String dopost(Model model, @ModelAttribute PostingForm form) {
		// 投稿内容情報の生成
		Posting posting = new Posting();
//		item.setId(form.getItem_id());

		// 入力値をリポジトリ保存
		posting.setContents(form.getContents());
		posting.setTitle(form.getTitle());
//		review.setItem(item);

		postingRepository.save(posting);
		return "redirect:/snssns/complete";
	}

	// 投稿完了画面表示
	@RequestMapping("/snssns/complete")
	public String doposted() {

		return "posting/posting_complete";
	}

	//コメントページ遷移処理
		@RequestMapping("/snssns/comments")
		public String goComments(Model model,@ModelAttribute CommentForm form) {
			return "comment/comment_page";
		}
	
	//コメント機能 入力チェック付き
	@RequestMapping("/snssns/comment")
	public String docomment(Model model ,@Valid @ModelAttribute CommentForm  form, BindingResult result) {
		if (result.hasErrors()) {
			//別のところのこーど
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList",errorList);
			return goComments(model,form);
		}
		String commentContent = form.getCommentContents();
//		content = form.getTitle();
		if (commentContent != null) {
			model.addAttribute("contents", commentContent);
			//条件を満たした場合コメント内容確認画面へ
			return "comment/comment_check";
		} else {
			return goComments(model,form);
		}
	}
	
	// コメントするボタン
	@RequestMapping("/snssns/doComment")
	public String docomment(Model model, @ModelAttribute CommentForm form) {
		// コメント内容情報の生成
		Comment comment = new Comment();
		
		 
//        System.out.println(timestamp);
		
//		item.setId(form.getItem_id());

		// 入力値をリポジトリ保存
		comment.setCommentContents(form.getCommentContents());
//		comment.setTitle(form.getTitle());
//		review.setItem(item);

		commentRepository.save(comment);
		return "redirect:/snssns/commentComplete";
	}
	
	// コメント完了画面表示
	@RequestMapping("/snssns/commentComplete")
	public String docommented() {
		return "comment/comment_complete";
	}
	
//	コメント数カウント処理
	@RequestMapping("/snssns/countComment")
	public String countcomment(long commentId) {
		Comment commentContents = new Comment();
		commentContents.setCommentId(commentId);
		
//	@RequestMapping("/comment/list/{id}")
//		public String showCommentList(Model model,@ModelAttribute PostingForm form) {
//			List<Posting> posting = postingRepository.findBy();
//			List<Comment> postings = commentRepository.findAll();
//			if (!posting.isEmpty()) {
//				session.setAttribute("posting", posting);
//			} else {
//				model.addAttribute("errMessage", "コメントは存在しません。");
//			}
//			return "index/index";
//		}
		
//		List<Comment> comment = new ArrayList<Comment>;
//		String commentContents = ();
//				commentRepository.findByCommentContentsContaining(commentContents);
	
		return "comment/comment_complete";
	}
//	コメント一覧表示
	@RequestMapping("comment/list/{id}")
	public String showCommentList(@PathVariable long id, Model model) {

		// 選択されたコメントIDに該当するレビュー情報を取得
		List<Comment> comment = commentRepository.findByPostingId(id);

		// コメント情報をViewへ渡す
		model.addAttribute("posthing_comment", comment);
		model.addAttribute("posthing_id",id);

		return "/comment/comment_read";
	}
	
	
//	レビューのコード引っ張ってきたもの
//	@RequestMapping("comment/list/{id}")
//	public String showCommentLists(@PathVariable int id, Model model) {

		// 選択された商品IDに該当するレビュー情報を取得
//		List<Comment> comment = CommentRepository.findByIdOrderByInsertDateDesc(id);

		// レビュー情報をViewへ渡す
//		model.addAttribute("posthing_comment", comment);
//		model.addAttribute("posthing_id",id);
//
//		return "review/list/review_list";
//	}
	
	// いいね実行
//	@RequestMapping("/like/{postId}")
////	@Transactional
//	public String Like(@PathVariable("postId") int postId, @ModelAttribute("like")Like like, Model model,Authentication loginUser) {
////		session.getAttribute("userId");
//		
	
	  //すでにいいねしていた場合、いいねを取り消す
//	  if(likeRepository.existsByUserAndPosting(loginUser.getPosting(), postId) == true) {
//	      likeRepository.deleteByUserAndPosting(loginUser.getName(), postId);
//	  }else {  //いいねしていなかった場合、投稿へのいいねを登録する
//	    like.setPosting(posting);
//	    like.setUser(session.getId());
////	    LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
////	    like.setCreatedAt(ldt);
//	    likeRepository.save(like);
//	  }
//		}
//	@RequestMapping("/like/{postId}")
//	@Transactional
//	public String Like(@PathVariable("postId") int postId, @ModelAttribute("like") Like like, Authentication loginUser, Model model) {
//	  //すでにいいねしていた場合、いいねを取り消す
//	  if(likeRepository.existsByUsernameAndPostId(loginUser.getName(), postId) == true) {
//	      likeRepository.deleteByUsernameAndPostId(loginUser.getName(), postId);
//	  }else {  //いいねしていなかった場合、投稿へのいいねを登録する
//	    like.setPostId(postId);
//	    like.setUsername(loginUser.getName());
//	    LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
//	    like.setCreatedAt(ldt);
//	    likeRepository.save(like);
//	  }
//	  return "redirect:/index/index";
//	    return "redirect:/postmain?postdetail";
//	}
//	@RequestMapping("/postmain")
//	
//	  public String goToPost(@ModelAttribute("posts") Posting posting,  Model model) {
//	    //投稿ごとの「いいね」の総数を取得※1
//	    Map<Integer, BigInteger> likeCount = likeRepository.findLikeCount();
//	    model.addAttribute("user", userRepository.findByUserId(session.getAttribute(userId)));
//	    model.addAttribute("posts", postingRepository.findAll());
//	    model.addAttribute("likeCount", likeCount);
//	    //自分が「いいね」した投稿一覧を取得※２
//	    model.addAttribute("myLikes", likeRepository.findMyLikes(loginUser.getName()));
//
//	  return "postmain";
//	}

	
}

