package com.my.house.dto;

public class UserDto {

	private int userNo;
    private String userName;
    private String userId;
    private String userPassword;
    private String userEmail;
    private String userPhone; 
    private String userPosition;
    private String userProfileImageUrl;
    
    public UserDto() {
    	super();
    }
    
	
	public UserDto(int userNo, String userName, String userId, String userPassword, String userEmail, String userPhone,
			String userPosition, String userProfileImageUrl) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.userPosition = userPosition;
		this.userProfileImageUrl = userProfileImageUrl;
	}


	public String getUserPhone() {
		return userPhone;
	}


	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}


	public String getUserPosition() {
		return userPosition;
	}


	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}


	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserProfileImageUrl() {
		return userProfileImageUrl;
	}
	public void setUserProfileImageUrl(String userProfileImageUrl) {
		this.userProfileImageUrl = userProfileImageUrl;
	}

	@Override
	public String toString() {
		return "UserDto [userNo=" + userNo + ", userName=" + userName + ", userId=" + userId + ", userPassword="
				+ userPassword + ", userEmail=" + userEmail + ", userProfileImageUrl=" + userProfileImageUrl + "]";
	}
    
    
}
