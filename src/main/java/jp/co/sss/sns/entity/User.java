package jp.co.sss.sns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sns_user")
public class User {

	/**
	 * 会員id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * 会員名
	 */
	@Column
	private String userName;
	/**
	 * パスワード
	 */
	@Column
	private String password;

	/**
	 * 権限
	 */
	@Column
	private Integer authority;

	/**
	 * 削除フラグ 0:未削除、1:削除済み
	 */
	@Column(insertable = false)
	private Integer deleteFlag;
	
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

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
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
