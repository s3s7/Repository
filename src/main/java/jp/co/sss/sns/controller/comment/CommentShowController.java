package jp.co.sss.sns.controller.comment;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.repository.CommentRepository;

/**
 * コメント一覧表示機能のコントローラクラス
 *
 */
@Controller
public class CommentShowController {

//	DI
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	HttpSession session;

//		コメント一覧表示
	@RequestMapping("comment/list/{id}")
	public String showCommentList(@PathVariable int id, Model model) {

		// 選択されたコメントIDに該当するコメント情報を取得
		List<Comment> comment = commentRepository.findByPostingIdOrderByInsertDateDesc(id);

		// コメント情報をViewへ渡す
		model.addAttribute("posting_comment", comment);
		model.addAttribute("posting_id", id);

		return "/comment/comment_read";
	}
	
	
	// 記事情報を全件検索(古い順)
		@RequestMapping(path = "/sns/commentSort")
		public String commentSort(Model model) {
	
//			List<Object[]> commentList = commentRepository.findByPostingIdAndCountpOrderByCountpDesc();
			List<Object[]> commentList = commentRepository.findByPostingIdOrderByCountpDesc();
			
			// 記事情報をViewへ渡す
			model.addAttribute("posting", commentList);
			model.addAttribute("sortNumber", 3);
			return "index/index";
		}

}
