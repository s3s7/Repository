package jp.co.sss.sns.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CommentForm {
	@NotEmpty(message = "コメントする内容を入力してください")
	private String commentContents;

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}
}
