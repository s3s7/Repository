package jp.co.sss.sns.controller.Posting;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.entity.Comment;
import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.form.UserForm;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.service.PostingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PostingDeleteController {

	//	DI
	private final PostingRepository postingRepository;
	private final PostingService postingService;
	
	/**
	 * 記事情報削除処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "post/delete/post_delete_check" 記事情報 削除確認画面へ
	 */
	@RequestMapping(path = "/post/delete/check/{id}", method = RequestMethod.GET)
	public String deletePostCheck(@PathVariable int id,Model model) {
		//int id = 0;
	//	 Posting posting = postingRepository.getReferenceById(postingId);
		Posting posting = postingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid posting Id:" + id));
			if (posting != null) {
				model.addAttribute("posting", posting);
			} else {
				model.addAttribute("errMessage", "投稿記事は存在しません。");
			}
		
		return "posting/delete/posting_delete_check";
	}
	

	/**
	 * 記事情報削除処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "post/delete/post_delete_check" 記事情報 削除確認画面へ
	 */
	@RequestMapping(path = "/post/delete/complete", method = RequestMethod.GET)
	public String deletePostComplete(Model model,@ModelAttribute UserForm form,HttpSession session,int postingId) {

		 Posting post = new Posting();
		//UserBean userBean = (UserBean) session.getAttribute("users");
		// 削除対象の記事情報を取得
		postingService.deletePosting(post.getId());

		// 記事情報をViewに渡す
	//	model.addAttribute("post", userBean);
	//	return "post/delete/post_delete_check";
		return "redirect:/post/delete/complete";
	}
	/**
	 * 記事情報削除完了処理
	 *
	 * @param session セッション情報
	 * @return "post/delete/post_delete_complete" 記事情報 削除完了画面へ
	 */
//	@RequestMapping(path = "/post/delete/complete", method = RequestMethod.POST)
//	public String deletePostComplete(HttpSession session) {
//
//		UserBean userBean = new UserBean();
//		userBean = (UserBean) session.getAttribute("users");
//
//		// 削除対象の記事情報を取得
//		//Posting post = postingRepository.getReferenceById(userBean.getId());
//		
//		// 記事情報を保存
//	//	postingRepository.save(post);
//
//		return "redirect:/post/delete/complete";
//	}

	/**
	 * 記事情報削除完了処理
	 *
	 * @param session セッション情報
	 * @return "post/delete/post_delete_complete" 記事情報 削除完了画面へ
	 */
//	@RequestMapping(path = "/post/delete/complete", method = RequestMethod.GET)
//	public String deleteCompleteRedirect(HttpSession session) {
//
//		// セッション情報を無効にする
//		session.invalidate();
//		return "post/delete/post_delete_complete";
//	}

	/**
	 * 記事情報 削除確認処理から記事詳細画面に戻る処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "/post/detail/post_detail" 記事情報 詳細画面へ
	 */
//	@RequestMapping(path = "/post/delete/check", method = RequestMethod.GET)
//	public String deletePostBack(Model model, HttpSession session) {
//
//		UserBean userBean = new UserBean();
//		userBean = (UserBean) session.getAttribute("users");
//
//		// 削除対象の記事情報を取得
//		Posting post = postingRepository.getOne(userBean.getId());
//
//		// postエンティティの各フィールドの値をUserBeanにコピー
//		BeanUtils.copyProperties(post, userBean);
//
//		// 記事情報をViewに渡す
//		model.addAttribute("post", userBean);
//
//		return "post/detail/post_detail";
//	}
	
	
}
