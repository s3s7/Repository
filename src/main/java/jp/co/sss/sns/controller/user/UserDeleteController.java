package jp.co.sss.sns.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.bean.UserBean;
import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.form.UserForm;
import jp.co.sss.sns.repository.UserRepository;
import jp.co.sss.sns.service.UserService;
import jp.co.sss.sns.util.Constant;

//@RequiredArgsConstructor 
@Controller
class UserDeleteController {

	/**
	 * 会員情報
	 */
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	/**
	 * セッション
	 */
	@Autowired
	HttpSession session;

	/**
	 * 会員情報削除確認処理
	 *
	 * @param model Viewとの値受渡し
	 * @param session セッション情報
	 * @return "user/delete/user_delete_check" 会員情報 削除確認画面へ
	 */
//	@PostMapping(path = "/sns/user/delete/check")
	@RequestMapping("/snssns/user/delete/checks")
	public String deleteCheck(Model model,@ModelAttribute UserForm form) {

		UserBean userBean = new UserBean();
		userBean = (UserBean) session.getAttribute("user");
		System.out.println("a");
		// 削除対象の会員情報を取得
		User user = userRepository.getOne(userBean.getId());

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
		userBean = (UserBean) session.getAttribute("user");

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
		@Transactional
		@GetMapping("/deleteUser")
		public String deleteUser(Authentication loginUser) {

			userService.deleteUserInfo(loginUser.getName());
			

			return "redirect:/logout?setting";
		}
	
}
