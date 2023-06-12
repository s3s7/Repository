package jp.co.sss.sns.controller.goal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.form.GoalForm;

@Controller
public class dayGoalRegistController {

	/**
	 * 会員情報入力画面表示処理
	 *
	 * @return "user/regist/user_regist_input" 会員情報 登録入力画面へ
	 */
	@RequestMapping(path = "/user/regist/input", method = RequestMethod.GET)
	public String registInput(GoalForm form) {
		return "user/regist/user_regist_input";
	}
	
	
}
