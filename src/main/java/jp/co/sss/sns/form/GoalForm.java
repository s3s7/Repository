package jp.co.sss.sns.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GoalForm {
	/**
	 * 目標ID
	 */
	private Integer id;

	/**
	 * 今日の目標
	 */
	@NotBlank
	@Size(max = 20)
	private String dailyGoalContents;
	
	/**
	 * 今週の目標
	 */
//	@NotBlank
//	@Size(max = 30)
//	private String weekGoalContents;
//	
//	/**
//	 * 今月の目標
//	 */
//	@NotBlank
//	@Size(max = 30)
//	private String monthGoalContents;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDailyGoalContents() {
		return dailyGoalContents;
	}

	public void setDailyGoalContents(String dailyGoalContents) {
		this.dailyGoalContents = dailyGoalContents;
	}

//	public String getWeekGoalContents() {
//		return weekGoalContents;
//	}
//
//	public void setWeekGoalContents(String weekGoalContents) {
//		this.weekGoalContents = weekGoalContents;
//	}
//
//	public String getMonthGoalContents() {
//		return monthGoalContents;
//	}
//
//	public void setMonthGoalContents(String monthGoalContents) {
//		this.monthGoalContents = monthGoalContents;
//	}

	
	
	
	
}
