package jp.co.sss.sns.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.form.PostingForm;
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

	// 投稿記事一覧表示機能
	@RequestMapping("/snssns/findAll")
	public String showList(Model model, @ModelAttribute PostingForm form) {

		List<Posting> posting = postingRepository.findAll();
		if (!posting.isEmpty()) {
			session.setAttribute("posting", posting);
		} else {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}
		return "index/index";
	}

	// 投稿ページ遷移処理
	@RequestMapping("/snssns/posts")
	public String postsposts(Model model, @ModelAttribute PostingForm form) {
		return "posting/posting_page";
	}

	// 投稿確認画面へ（登録）機能 入力チェックあり
	@RequestMapping("/snssns/posting")
	public String doLogin(Model model, @Valid @ModelAttribute PostingForm form, BindingResult result) {
		if (result.hasErrors()) {
			// エラーリスト
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return postsposts(model, form);
		}
		String content = form.getContents();
		content = form.getTitle();

		if (content != null) {
			//List<ItemBean> itemBeanList = BeanCopy.copyEntityToItemBean(itemList.getContent());
			model.addAttribute("contents", content);
			// 条件を満たした場合投稿内容確認画面へ
			return "posting/posting_check";

		} else {
			return postsposts(model, form);
		}

	}

	// 投稿するボタン
	@RequestMapping("/snssns/dopost")
	public String dopost(Model model, @ModelAttribute PostingForm form) {
		// 投稿内容情報の生成
		Posting posting = new Posting();

		// 入力値をリポジトリ保存
		posting.setContents(form.getContents());
		posting.setTitle(form.getTitle());
		// 投稿時間の取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date date = new Date();
		//SimpleDateFormatクラスのparseメソッドを使うにはthrows句を使ってParseExceptionなどに例外を投げるか、try-catch構文で例外処理を行う必要
		try {
			date = sdf.parse(DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(LocalDateTime.now()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		posting.setInsertDate(date);
		postingRepository.save(posting);
		return "redirect:/snssns/complete";
	}

	// 投稿完了画面表示
	@RequestMapping("/snssns/complete")
	public String doposted() {
		return "posting/posting_complete";
	}

	@RequestMapping(path = "/sns/newSort")
	public String newSort(Model model, Pageable pageable) {
		// 商品情報を全件検索(新着順)
		Page<Posting> postingList = postingRepository.findByOrderByInsertDateDesc(pageable);
		// エンティティ内の検索結果をJavaBeansにコピー
//		List<PostingBean> postingBeanList = BeanCopy.copyEntityToItemBean(postingList.getContent());

		// 商品情報をViewへ渡す
		model.addAttribute("posthing", postingList);
		return "index/index";
	}
	// いいね実行
//	@RequestMapping("/like/{postId}")
////	@Transactional
//	public String Like(@PathVariable("postId") int postId, @ModelAttribute("like")Like like, Model model,Authentication loginUser) {
////		session.getAttribute("userId");
//		

	// すでにいいねしていた場合、いいねを取り消す
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
