package jp.co.sss.sns.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LoginForm {
	@NotNull
	private String userId;
	@NotNull
    private String password;


	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
