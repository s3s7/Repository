package jp.co.sss.sns.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sns_like")

public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long likeId;

	@ManyToOne
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Posting posting;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;


}
