package com.my.house.dto;

import java.util.List;

public class UserResultDto {
	private int result;
	private List<Integer> userFavoriteList;
	
	public List<Integer> getUserFavoriteList() {
		return userFavoriteList;
	}
	public void setUserFavoriteList(List<Integer> userFavoriteList) {
		this.userFavoriteList = userFavoriteList;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
}
