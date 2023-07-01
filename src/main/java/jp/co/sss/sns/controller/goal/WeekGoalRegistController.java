package jp.co.sss.sns.controller.goal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.form.GoalForm;
@Controller
public class WeekGoalRegistController {
		/**
		 * 今月の目標入力画面表示処理
		 *
		 * @return "" 今月の目標 登録入力画面へ
		 */
		@RequestMapping(path = "/sns/weekGoal/input", method = RequestMethod.GET)
		public String weekGoalRegist(GoalForm form) {
			return "goal/week_goal_input";
		}
		
		/**
		 * POSTメソッドを利用して会員情報入力画面に戻る処理
		 *
		 * @param form 会員情報
		 * @return "user/regist/user_regist_input" 会員情報 登録入力画面へ
		 */
//		@RequestMapping(path = "/goal/week_goal/input", method = RequestMethod.POST)
//		public String registInputBack(UserForm form) {
//			return "user/regist/user_regist_input";
//		}
	//
//		/**
//		 * 今週の目標 登録確認処理
//		 *
//		 * @param form   会員情報フォーム
//		 * @param result 入力チェック結果
//		 * @return 入力値エラーあり："goal/week_goal_input" 目標情報登録画面へ
//		 *         入力値エラーなし："goal/week_goal_check" 目標情報登録確認画面へ
//		 */
		@RequestMapping(path = "/sns/weekGoal/check", method = RequestMethod.POST)
		public String weekGoalRegistCheck(@Valid @ModelAttribute GoalForm form, BindingResult result) {
			// 入力値にエラーがあった場合、目標 入力画面表示処理に戻る
			if (result.hasErrors()) {
				return "goal/week_goal_input";
			}
			return "goal/week_goal_check";
		}

		/**
		 * 会員情報登録完了処理
		 *
		 * @param form 会員情報
		 * @return "redirect:/user/regist/complete" 会員情報 登録完了画面へ
		 */
//		@RequestMapping(path = "/user/regist/complete", method = RequestMethod.POST)
//		public String registComplete(@ModelAttribute UserForm form) {
//			// 会員情報の生成
//			User user = new User();
	//
//			// 入力値を会員情報にコピー
//			BeanUtils.copyProperties(form, user);
	//
//			// 会員情報を保存
//			userRepository.save(user);
	//
//			// ログイン状態保存
//			UserBean userBean = new UserBean();
//			userBean.setId(user.getId());
//			userBean.setName(user.getName());
//			userBean.setAuthority(user.getAuthority());
//			session.setAttribute("user", userBean);
	//
//			return "redirect:/user/regist/complete";
//		}
	//
//		/**
//		 * 会員情報登録完了画面表示
//		 *
//		 * @param form 会員情報
//		 * @return "user/regist/user_regist_complete" 会員情報 登録完了画面へ
//		 */
//		@RequestMapping(path = "/user/regist/complete", method = RequestMethod.GET)
//		public String registCompleteRedirect() {
//			return "user/regist/user_regist_complete";
//		}
}
