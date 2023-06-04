package jp.co.sss.sns.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.bean.UserBean;
import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.repository.UserRepository;

@Controller
public class UserShowCustomerController {
	/**
	 * 会員情報
	 */
	@Autowired
	UserRepository userRepository;
	// 会員詳細画面表示処理
	@RequestMapping(path = "sns/user/detail")
	public String userShowCostomer(Model model, HttpSession session) {

		UserBean userBean = new UserBean();
		userBean = (UserBean) session.getAttribute("users");

		// 表示対象の会員情報を取得
		// User user = userRepository.getOne(userBean.getId());
		User user = new User();
		//getReferenceByIdは例外が起こりやすいため例外処理
		try {
			user =userRepository.getReferenceById(userBean.getId());
		} catch (RuntimeException e) {
			model.addAttribute("userDetailErrMessage", "会員情報は存在しません。");
			return "index/index";
		}

		// Userエンティティの各フィールドの値をUserBeanにコピー
		BeanUtils.copyProperties(user, userBean);

		//会員情報をViewに渡す
		model.addAttribute("users", userBean);

		return "user/detail/user_detail";

	}

}
