package jp.co.sss.sns.bean;

import javax.persistence.Column;

public class UserBean {

	private Integer id;

	private String userName;

	private String password;

	/**
	 * 権限
	 */
	private Integer authority;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
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
