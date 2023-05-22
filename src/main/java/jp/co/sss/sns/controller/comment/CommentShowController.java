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

		/**
		 * 商品レビュー情報
		 */
		@Autowired
		CommentRepository commentRepository;

		/**
		 * セッション情報
		 */
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
		
		/**
		 * 商品レビュー一覧表示画面 表示処理
		 *
		 * @param model    Viewとの値受渡し
		 * @return "/review/list/review_list" 商品レビュ一覧画面へ
		 */
//		@RequestMapping(path = "/comment/list/{id}")
//		public String showCommentList(@PathVariable int id, Model model) {
//
//			// 選択された記事IDに該当するコメント情報を取得
//			List<Comment> comment = commentRepository.findByPostingIdOrderByInsertDateDesc(id);
//
//			// レビュー情報をViewへ渡す
////			model.addAttribute("item_reviews", review);
//			model.addAttribute("comment_contents", comment);
//			model.addAttribute("posting_id",id);
//
//			return "comment/list/comment_all";
		}
		
		
		
