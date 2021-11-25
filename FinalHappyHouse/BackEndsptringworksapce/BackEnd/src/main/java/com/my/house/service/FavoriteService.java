package com.my.house.service;

import com.my.house.dto.FavoriteResultDto;
public interface FavoriteService {
	public FavoriteResultDto addFavorite(int userNo, int agentNo);
	
	public FavoriteResultDto removeFavorite(int userNo, int agentNo);


}