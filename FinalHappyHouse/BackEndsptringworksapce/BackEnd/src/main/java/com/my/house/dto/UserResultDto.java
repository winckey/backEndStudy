package com.my.house.dto;

import java.util.List;

public class UserResultDto {
	private int result;
	private List<Integer> userFavoriteAgentList;
	
	public List<Integer> getUserFavoriteAgentList() {
		return userFavoriteAgentList;
	}
	public void setUserFavoriteAgentList(List<Integer> userFavoriteAgentList) {
		this.userFavoriteAgentList = userFavoriteAgentList;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
