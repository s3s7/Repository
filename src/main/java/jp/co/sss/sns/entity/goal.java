package jp.co.sss.sns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sns_goal")
public class goal {

	/**
	 * 目標id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 今日の目標
	 */
	@Column
	private String dayGoal;
	
	/**
	 * 今週の目標
	 */
	@Column
	private String weekGoal;
	
	/**
	 * 今月の目標
	 */
	@Column
	private String monthGoal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDayGoal() {
		return dayGoal;
	}

	public void setDayGoal(String dayGoal) {
		this.dayGoal = dayGoal;
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
