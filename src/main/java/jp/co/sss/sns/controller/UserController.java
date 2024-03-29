package jp.co.sss.sns.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.bean.UserBean;
import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.form.LoginForm;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
class UserController {

	//DI
	private final UserRepository userrepository;
	private final HttpSession session;
	private final PostingRepository postingrepository;
	private final PostingController postingController;
	
	// ログインページ
	@RequestMapping("/snssns/index")
	public String index() {
		return "login";
	}

	//ログアウト
	@RequestMapping(path = "/snssns/logout")
	public String Logout(Model model) {
		List<Posting> posting = postingrepository.findAll();
		
		if (!posting.isEmpty()) {
			session.setAttribute("posting", posting);
		} else {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}
		
		session.removeAttribute("users");
		return "/index/index";
	}

	// ログイン機能 入力チェックあり
	@PostMapping(path = "/snssns/doLogin")
	public String Login(@Valid @ModelAttribute LoginForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errMessage", "ユーザIDまたはパスワードが間違っています。");
			return "index/index";
		}
		// ユーザ名とパスワードを取得
		String userName = form.getUserName();
		String password = form.getPassword();
		
		// 会員情報を検索
		User user = userrepository.findByUserNameAndPassword(userName, password);
		Integer userDeleteFlag = user.getDeleteFlag();
		UserBean userBean = new UserBean();

		if (user != null && userDeleteFlag < 1) {
			// 会員情報が存在する場合、ログイン
			BeanUtils.copyProperties(user, userBean);
			session.setAttribute("users", userBean);
			return postingController.showList(model);

		} else {
			// 会員情報がない場合エラーメッセージ
			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
			return "index/index";
		}

	}

}
