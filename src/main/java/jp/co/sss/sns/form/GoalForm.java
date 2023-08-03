package jp.co.sss.sns.form;

import java.sql.Time;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class GoalForm {

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
	
//	@NotBlank
//	private Time sleepyTime;
	
	public String getDailyGoalContents() {
		return dailyGoalContents;
	}

	public void setDailyGoalContents(String dailyGoalContents) {
		this.dailyGoalContents = dailyGoalContents;
	}

	public String getSleepyTime() {
		return sleepyTime;
	}

	public void setSleepyTime(String sleepyTime) {
		this.sleepyTime = sleepyTime;
	}
	
}
