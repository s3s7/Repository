package jp.co.sss.sns.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sns_comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long commentId;

	@ManyToOne
	@JoinColumn(name = "post_id", referencedColumnName = "id")
	private Posting posting;
	
@Column
private String commentContents;

public long getCommentId() {
	return commentId;
}

public void setCommentId(long commentId) {
	this.commentId = commentId;
}

public Posting getPosting() {
	return posting;
}

public void setPosting(Posting posting) {
	this.posting = posting;
}

public String getCommentContents() {
	return commentContents;
}

public void setCommentContents(String commentContents) {
	this.commentContents = commentContents;
}
	
	
}
