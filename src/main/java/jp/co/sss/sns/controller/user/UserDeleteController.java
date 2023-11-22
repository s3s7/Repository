package jp.co.sss.sns.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.bean.UserBean;
import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.form.UserForm;
import jp.co.sss.sns.repository.CommentRepository;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;
import jp.co.sss.sns.service.UserService;
import jp.co.sss.sns.util.Constant;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
class UserDeleteController {

//	DI
	private final UserRepository userRepository;
	private final UserService userService;

	/**
	 * 会員情報削除確認処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "user/delete/user_delete_check" 会員情報 削除確認画面へ
	 */
	@RequestMapping(path = "/snssns/user/delete/checks", method = RequestMethod.GET)
	public String deleteCheck(Model model,@ModelAttribute UserForm form,HttpSession session) {

		UserBean userBean = new UserBean();
		userBean = (UserBean) session.getAttribute("users");
		System.out.println("a");
		// 削除対象の会員情報を取得
		User user = userRepository.getReferenceById(userBean.getId());

		// Userエンティティの各フィールドの値をUserBeanにコピー
		BeanUtils.copyProperties(user, userBean);

		// 会員情報をViewに渡す
		model.addAttribute("user", userBean);
		return "user/delete/user_delete_check";
	}

	/**
	 * 会員情報削除完了処理
	 *
	 * @param session セッション情報
	 * @return "user/delete/user_delete_complete" 会員情報 削除完了画面へ
	 */
	@RequestMapping(path = "/user/delete/complete", method = RequestMethod.POST)
	public String deleteComplete(HttpSession session) {

		UserBean userBean = new UserBean();
		userBean = (UserBean) session.getAttribute("users");

		// 削除対象の会員情報を取得
		User user = userRepository.getReferenceById(userBean.getId());
		// 削除フラグを立てる
		user.setDeleteFlag(Constant.DELETED);
		
		// 会員情報を保存
		userRepository.save(user);

		return "redirect:/user/delete/complete";
	}

	/**
	 * 会員情報削除完了処理
	 *
	 * @param session セッション情報
	 * @return "user/delete/user_delete_complete" 会員情報 削除完了画面へ
	 */
	@RequestMapping(path = "/user/delete/complete", method = RequestMethod.GET)
	public String deleteCompleteRedirect(HttpSession session) {

		// セッション情報を無効にする
		session.invalidate();
		return "user/delete/user_delete_complete";
	}

	/**
	 * 会員情報 削除確認処理から会員詳細画面に戻る処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "/user/detail/user_detail" 会員情報 詳細画面へ
	 */
	@RequestMapping(path = "/user/delete/check", method = RequestMethod.GET)
	public String deleteBack(Model model, HttpSession session) {

		UserBean userBean = new UserBean();
		userBean = (UserBean) session.getAttribute("user");

		// 削除対象の会員情報を取得
		User user = userRepository.getOne(userBean.getId());

		// Userエンティティの各フィールドの値をUserBeanにコピー
		BeanUtils.copyProperties(user, userBean);

		// 会員情報をViewに渡す
		model.addAttribute("user", userBean);

		return "user/detail/user_detail";
	}
	
	// ユーザーを削除
//		@Transactional
//		@PostMapping("/deleteUser")
//		public String deleteUser(Authentication loginUser) {
//
//			userService.deleteUserInfo(loginUser.getName());
//
//			return "user/delete/item_delete_check";
//		}
			

		}
	
