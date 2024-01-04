package jp.co.sss.sns.controller.comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.form.CommentForm;
import jp.co.sss.sns.repository.CommentRepository;

@Controller
public class CommentRegistController {
	// DI
	@Autowired
	CommentRepository commentRepository;

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
	@RequestMapping("/snssns/comment/complete")
	public String commentComplete(Model model, @ModelAttribute CommentForm form) {
		// コメント内容情報の生成
		Comment comment = new Comment();

		// 入力値をリポジトリ保存
		comment.setCommentContents(form.getCommentContents());
		// 投稿時間の取得
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm");
		Date date = new Date();
		// SimpleDateFormatクラスのparseメソッドを使うにはthrows句を使ってParseExceptionなどに例外を投げるか、try-catch構文で例外処理を行う必要
		try {
			date = sdf.parse(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		comment.setInsertDate(date);
		commentRepository.save(comment);
		return "redirect:/snssns/commentComplete";
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
}}
