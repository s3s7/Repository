package jp.co.sss.sns.controller.comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.service.PostingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentRegistController {
	// DI
	private final CommentRepository commentRepository;
	private final PostingRepository postingRepository;
	private final HttpSession session;

	// コメントページ遷移処理
	@RequestMapping("/snssns/commentPage")
	public String commentPage(Model model, @ModelAttribute CommentForm form) {
		return "comment/comment_page";
	}

	// コメント機能
	@RequestMapping("/snssns/commentInput")
	public String commentInput(Model model, @Valid @ModelAttribute CommentForm form, BindingResult result) {
		if (result.hasErrors()) {
			// エラーが発生した場合
			List<ObjectError> errorList = result.getAllErrors();
			model.addAttribute("errorList", errorList);
			return commentPage(model, form);
		}
		String commentContent = form.getCommentContents();
		
		if (commentContent != null) {
			model.addAttribute("contents", commentContent);
			// 条件を満たした場合コメント内容確認画面へ
			return "comment/comment_check";
		} else {
			return commentPage(model, form);
		}
	}

	// コメント投稿ボタン
	@RequestMapping("/snssns/comment/complete/{id}")
//	public String commentComplete(@PathVariable int id,Model model, @Valid @ModelAttribute CommentForm form) {
	public String commentComplete(@PathVariable int id,Model model, @Valid @ModelAttribute Comment comment) {
		//		// コメント内容情報の生成
//		Comment comment = new Comment();
////		postingのIdをとってきたい。それを外部キーとしてセットしたい。　現在の
//		
//		session.setAttribute("postingId", id);
//		
//		// 入力値をリポジトリ保存
//		comment.setCommentContents(form.getCommentContents());
//		// 投稿時間の取得
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
//		Date date = new Date();
//		// SimpleDateFormatクラスのparseメソッドを使うにはthrows句を使ってParseExceptionなどに例外を投げるか、try-catch構文で例外処理を行う必要
//		try {
//			date = sdf.parse(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		
//		comment.setInsertDate(date);
//		comment.setPostingId(id);
//		commentRepository.save(comment);
//		return "redirect:/snssns/comment/complete";
//		
		 // 投稿を取得
	    Posting posting = postingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid posting Id:" + id));
	    
	    // コメントに投稿IDを設定
	    comment.setPostingId(posting.getId());
	    // コメントを保存
	    commentRepository.save(comment);
	    
	    // 投稿ページにリダイレクト
	    return "redirect:/snssns/comment/complete" + id;
	}

	// コメント完了画面表示
	@RequestMapping("/snssns/commentComplete")
	public String commentComplete() {
		return "comment/comment_complete";
	}

	// コメント数カウント処理
	@RequestMapping("/snssns/commentCount")
	public String commentCount(int commentId) {
		Comment commentContents = new Comment();
		commentContents.setCommentId(commentId);

	return"comment/comment_complete";
	}
}
