package jp.co.sss.sns.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
	/**
	 * 会員ID
	 */
	private Integer	id;

//	/**
//	 * 会員メールアドレス
//	 */
//	@NotBlank
//	@Email
//	private String	email;


	/**
	 * パスワード
	 */
	@NotBlank
	@Size(min = 8, max = 16)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	private String	password;
	/**
	 * ユーザー名
	 */
	@NotNull
    private String userId;

	
	/**
	 * 権限
	 */
	private Integer authority;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
}
