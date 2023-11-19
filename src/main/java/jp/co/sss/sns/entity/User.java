package jp.co.sss.sns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
