package jp.co.sss.sns.controller.comment;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.PostingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentShowController {

//	DI
	private final CommentRepository commentRepository;

//		コメント一覧表示
	@RequestMapping("comment/list/{id}")
	public String showCommentList(@PathVariable int id, Model model) {

		// 選択されたコメントIDに該当するコメント情報を取得
		List<Comment> comment;
		try {
			 comment = commentRepository.findByPostingIdOrderByInsertDateDesc(id);
			// コメント情報をViewへ渡す
				model.addAttribute("posting_comment", comment);
				model.addAttribute("posting_comment_id", id);
				model.addAttribute("posting_id", id);
		} catch (Exception e) {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}

		return "/comment/comment_read";
	}
	
}
