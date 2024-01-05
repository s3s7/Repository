package jp.co.sss.sns.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sns_comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;

//	@ManyToOne
	@JoinColumn(name = "posting_id", referencedColumnName = "id")
	@Column(name = "posting_id")
//	private Posting postingId;
	private int postingId;
	

	@Column
	private String commentContents;
	
	@Column
	private Date insertDate;

	
}
