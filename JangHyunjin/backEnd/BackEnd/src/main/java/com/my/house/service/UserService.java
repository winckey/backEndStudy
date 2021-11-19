package com.my.house.service;

import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;

public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
}
