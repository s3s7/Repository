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
	private String dailyGoal;
	
	/**
	 * 今週の目標
	 */
	@NotBlank
	@Size(max = 30)
	private String weekGoal;
	
	/**
	 * 今月の目標
	 */
	@NotBlank
	@Size(max = 30)
	private String monthGoal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDailyGoal() {
		return dailyGoal;
	}

	public void setDailyGoal(String dailyGoal) {
		this.dailyGoal = dailyGoal;
	}

	public String getWeekGoal() {
		return weekGoal;
	}

	public void setWeekGoal(String weekGoal) {
		this.weekGoal = weekGoal;
	}

	public String getMonthGoal() {
		return monthGoal;
	}

	public void setMonthGoal(String monthGoal) {
		this.monthGoal = monthGoal;
	}
	
	
	
	
}
