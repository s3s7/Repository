package jp.co.sss.sns.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class WeekGoalForm {

	/**
	 * 今週の目標
	 */
	@NotBlank
	@Size(max = 30)
	private String weekGoalContents;
	
	public String getWeekGoalContents() {
	return weekGoalContents;
}

public void setWeekGoalContents(String weekGoalContents) {
	this.weekGoalContents = weekGoalContents;
}

	
}
