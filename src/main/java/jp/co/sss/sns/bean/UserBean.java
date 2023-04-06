package jp.co.sss.sns.bean;



public class UserBean {

	  private long id;

	    private String userId;
	    
	    private String password;
	    
//	    private String userName;
		/**
		 * 権限
		 */
		private Integer	authority;


		public long getId() {
			return id;
		}


		public void setId(long id) {
			this.id = id;
		}


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


//		public String getUserName() {
//			return userName;
//		}
//
//
//		public void setUserName(String userName) {
//			this.userName = userName;
//		}


		public int getAuthority() {
			return authority;
		}


		public void setAuthority(Integer authority) {
			this.authority = authority;
		}

	
}
