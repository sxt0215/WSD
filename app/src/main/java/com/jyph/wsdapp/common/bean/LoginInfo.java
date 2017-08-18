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


	public static class UserInfo{
		private String userMobile;
		private String userNmae;
		private String userNumber;
		private String userId;
		private boolean emergency;//紧急联系人
		private boolean identity;//身份信息
		private boolean otherInfo;//其他信息
		private boolean undetermined;//待定

		public UserInfo(String userName, String userId, String mobile, String s, int i, String s1) {
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public boolean isEmergency() {
			return emergency;
		}

		public void setEmergency(boolean emergency) {
			this.emergency = emergency;
		}

		public boolean isIdentity() {
			return identity;
		}

		public void setIdentity(boolean identity) {
			this.identity = identity;
		}

		public boolean isOtherInfo() {
			return otherInfo;
		}

		public void setOtherInfo(boolean otherInfo) {
			this.otherInfo = otherInfo;
		}

		public boolean isUndetermined() {
			return undetermined;
		}

		public void setUndetermined(boolean undetermined) {
			this.undetermined = undetermined;
		}

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
