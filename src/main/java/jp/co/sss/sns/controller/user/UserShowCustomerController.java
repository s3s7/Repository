package jp.co.sss.sns.controller.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	

	@RequestMapping(path = "sns/user/show", method = RequestMethod.POST)

//会員詳細画面表示処理
	public String userShowCostomer(Model model,HttpSession session) {

UserBean userBean = new UserBean();
userBean = (UserBean) session.getAttribute("user");

//表示対象の会員情報を取得
User user = userRepository.getOne(userBean.getId());


		
		return "user/detail/user_detail";

	}

}
