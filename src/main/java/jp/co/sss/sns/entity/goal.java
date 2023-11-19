package jp.co.sss.sns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
			private String dayGoalContents;
			
			/**
			 * 今週の目標
			 */
			@Column
			private String weekGoalContents;
			
			/**
			 * 今月の目標
			 */
			@Column
			private String monthGoalContents;

		}

