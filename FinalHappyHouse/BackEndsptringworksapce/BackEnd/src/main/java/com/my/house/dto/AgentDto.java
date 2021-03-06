package com.my.house.dto;

public class AgentDto {

	private int agentNo;
	private int userNo;
	private String agentName;
	private String agentDesc;
	private String agentEmail;
	private String agentPhone;;
	private String agentProfileImageUrl;
	
	private int agentRate;
	private int userFavoriteCheck;
	
	public int getUserFavoriteCheck() {
		return userFavoriteCheck;
	}
	public void setUserFavoriteCheck(int userFavoriteCheck) {
		this.userFavoriteCheck = userFavoriteCheck;
	}
	public int getAgentNo() {
		return agentNo;
	}
	public void setAgentNo(int agentNo) {
		this.agentNo = agentNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentDesc() {
		return agentDesc;
	}
	public void setAgentDesc(String agentDesc) {
		this.agentDesc = agentDesc;
	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}

	public int getAgentRate() {
		return agentRate;
	}
	public void setAgentRate(int agentRate) {
		this.agentRate = agentRate;
	}
	public String getAgentProfileImageUrl() {
		return agentProfileImageUrl;
	}
	public void setAgentProfileImageUrl(String agentProfileImageUrl) {
		this.agentProfileImageUrl = agentProfileImageUrl;
	}
	@Override
	public String toString() {
		return "AgentDto [agentNo=" + agentNo + ", userNo=" + userNo + ", agentName=" + agentName + ", agentDesc="
				+ agentDesc + ", agentEmail=" + agentEmail + ", agentPhone=" + agentPhone + ", agentProfileImageUrl="
				+ agentProfileImageUrl + ", agentRate=" + agentRate + ", userFavoriteCheck=" + userFavoriteCheck + "]";
	}


}
