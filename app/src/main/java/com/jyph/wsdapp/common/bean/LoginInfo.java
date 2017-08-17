package com.jyph.wsdapp.common.bean;


public class LoginInfo extends BaseInfo{
	private UserInfo userInfo;
	private String source;

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	private class UserInfo{
		private String userMobile;
		private String userNmae;
		private String userNumber;

		public String getUserMobile() {
			return userMobile;
		}

		public void setUserMobile(String userMobile) {
			this.userMobile = userMobile;
		}

		public String getUserNmae() {
			return userNmae;
		}

		public void setUserNmae(String userNmae) {
			this.userNmae = userNmae;
		}

		public String getUserNumber() {
			return userNumber;
		}

		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}

	}







}
