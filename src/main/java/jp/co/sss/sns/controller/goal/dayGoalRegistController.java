package jp.co.sss.sns.controller.goal;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.sns.entity.goal;
import jp.co.sss.sns.form.GoalForm;
import jp.co.sss.sns.repository.GoalRepository;

@Controller
class dayGoalRegistController {

	/**
	 * 目標情報
	 */
	@Autowired
	GoalRepository goalRepository;
	
	/**
	 * セッション
	 */
	@Autowired
	HttpSession session;
	
	/**
	 * 今日の目標入力画面表示処理
	 *
	 * @return "" 今日の目標 登録入力画面へ
	 */
	@RequestMapping(path = "/sns/dailyGoal/input", method = RequestMethod.GET)
	public String dailyGoalRegistGet(Model model ,@ModelAttribute GoalForm form) {
		return "goal/daily_goal_input";
	}
	
	/**
	 * POSTメソッドを利用して会員情報入力画面に戻る処理
	 *
	 * @param form 会員情報
	 * @return "user/regist/user_regist_input" 会員情報 登録入力画面へ
	 */
//	@RequestMapping(path = "/sns/dailyGoal/input", method = RequestMethod.POST)
//	public String dailyGoalRegistPost(GoalForm form) {
//		return "user/regist/user_regist_input";
//	}

	/**
	 * 会員情報 登録確認処理
	 *
	 * @param form   目標フォーム
	 * @param result 入力チェック結果
	 * @return 入力値エラーあり："goal/daily_goal_input" 今日の目標登録画面へ
	 *         入力値エラーなし："goal/daily_goal_input" 今日の目標登録確認画面へ
	 */
	@RequestMapping(path = "/sns/dailyGoal/check")
	public String dailyGoalRegistCheck(@Valid @ModelAttribute GoalForm form, BindingResult result) {
		// 入力値にエラーがあった場合、今日の目標 入力画面表示処理に戻る
		if (result.hasErrors()) {
			return "goal/daily_goal_input";
		}
		return "goal/daily_goal_check";
	}

	/**
	 * 今日の目標情報登録完了処理
	 *
	 * @param form 会員情報
	 * @return "redirect:/goal/daily_goal_complete" 今日の目標 登録完了画面へ
	 */
	@RequestMapping(path = "/sns/dailyGoal/complete", method = RequestMethod.POST)
	public String dailyGoalRegistComplete(@ModelAttribute GoalForm form) {
		// 今日の目標情報の生成
		goal goal = new goal();
		
//		goal = (jp.co.sss.sns.entity.goal) dayGoalRegistController.getSelectedItems();

		// 入力値を情報にコピー
//		BeanUtils.copyProperties(form, goal);

		// 今日の目標情報を保存
		goalRepository.save(goal);
		// 目標保存
//		UserBean userBean = new UserBean();
//		userBean.setId(user.getId());
//		userBean.setName(user.getName());
//		userBean.setAuthority(user.getAuthority());
		session.setAttribute("goal", goal);

		return "/goal/daily_goal_complete";
	}

//	private static Map<String,String> getSelectedTimes(){
//	     Map<String, String> selectMap = new LinkedHashMap<String, String>();
//	     selectMap.put("key_A", "0:00");
//	     selectMap.put("key_B", "選択肢Ｂは、これですよ");
//	     selectMap.put("key_C", "選択肢Ｃは、これですよ");
//	     selectMap.put("key_D", "選択肢Ｄは、これですよ");
//	     selectMap.put("key_E", "選択肢Ｅは、これですよ");
//	     return selectMap;
//	 }   
	
	/**
	 * 会員情報登録完了画面表示
	 *
	 * @param form 今日の目標情報
	 * @return "goal/daily_goal_input" 今日の目標情報 登録完了画面へ
	 */
//	@RequestMapping(path = "/sns/dailyGoal/complete", method = RequestMethod.GET)
//	public String registCompleteRedirect() {
//		return "/goal/daily_goal_complete";
//	}
	
	
}
