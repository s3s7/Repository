package jp.co.sss.sns.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.sns.entity.Posting;
import jp.co.sss.sns.entity.User;
import jp.co.sss.sns.form.LoginForm;
import jp.co.sss.sns.repository.PostingRepository;
import jp.co.sss.sns.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	UserRepository userrepository;
	@Autowired
	HttpSession session;
	@Autowired
	PostingRepository postingrepository;

//ログインページ
	@RequestMapping("/snssns/index")
	public String index() {
		return "login";
	}

//ログアウト機能
	@RequestMapping(path = "/snssns/logout")
	public String doLogout(Model model) {
//		System.out.println("a");
		List<Posting> posting = postingrepository.findAll();
		if (!posting.isEmpty()) {
			session.setAttribute("posting", posting);
		} else {
			model.addAttribute("errMessage", "投稿記事は存在しません。");
		}
		session.removeAttribute("users");
		return "/index/index";
	}

//	ログイン機能　入力チェックあり
	@RequestMapping(path = "/snssns/doLogin")
	public String doLogin(@Valid @ModelAttribute LoginForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errMessage", "ユーザIDまたはパスワードが間違っています。");
			return "index/index";
		}
		String userId = form.getUserId();
		String password = form.getPassword();
//			List<User> user = 
		User user = userrepository.findByUserIdAndPassword(userId, password);
//user =  userrepository.findByUserIdAndPassword(userId, password);

		if (user != null) {
//				System.out.println(user.getUserId());
//				List<ItemBean> itemBeanList = BeanCopy.copyEntityToItemBean(itemList.getContent());
//				Lis> userr = new ArrayList<userr>();
			session.setAttribute("users", user.getUserId());
			return "index/index";

		} else {
			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
			return "index/index";
		}

//           return "redirect:/index";
	}
//	@RequestMapping(path = "/login", method = RequestMethod.POST)
//	public String doLogin(@Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session,
//			Model model) {
//		if (result.hasErrors()) {
//			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
//			return "redirect:/index";
//		}
//		String userId = form.getUserId();
//		String password = form.getPassword();
//		List<User> user = userrepository.findByUserIdAndPassword(userId, password);
//
//		if (user != null) {
//
//			session.setAttribute("users", ((User) user).getUserId());
//			return "redirect:/index";
//
//		} else {
//			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
//			return "redirect:/index";
//		}
//	}

}
