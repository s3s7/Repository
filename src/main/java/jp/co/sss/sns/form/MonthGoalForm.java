package jp.co.sss.sns.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MonthGoalForm {

	

	/**
	 * 今月の目標
	 */
	@NotBlank
	@Size(max = 30)
	private String monthGoalContents;


	public String getMonthGoalContents() {
		return monthGoalContents;
	}

	public void setMonthGoalContents(String monthGoalContents) {
		this.monthGoalContents = monthGoalContents;
	}

}
