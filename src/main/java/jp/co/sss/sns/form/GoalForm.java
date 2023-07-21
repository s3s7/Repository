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
	 * 就寝時刻
	 */
	@NotBlank
	private String sleepyTime;
	
	
	
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


	
	
	
	
}
