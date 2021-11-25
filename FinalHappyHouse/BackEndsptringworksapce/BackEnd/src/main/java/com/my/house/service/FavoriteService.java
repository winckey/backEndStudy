package com.my.house.service;

import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;
public interface FavoriteService {
	public FavoriteResultDto userRegister(UserDto userDto);
	
	public FavoriteResultDto userDelete(String userId);


}