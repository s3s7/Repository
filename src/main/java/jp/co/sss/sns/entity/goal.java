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

			public Integer getId() {
				return id;
			}

			public void setId(Integer id) {
				this.id = id;
			}

			public String getDayGoalContents() {
				return dayGoalContents;
			}

			public void setDayGoalContents(String dayGoalContents) {
				this.dayGoalContents = dayGoalContents;
			}

			public String getWeekGoalContents() {
				return weekGoalContents;
			}

			public void setWeekGoalContents(String weekGoalContents) {
				this.weekGoalContents = weekGoalContents;
			}

			public String getMonthGoalContents() {
				return monthGoalContents;
			}

			public void setMonthGoalContents(String monthGoalContents) {
				this.monthGoalContents = monthGoalContents;
			}

		}

