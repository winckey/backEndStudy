package com.my.house.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;
public interface UserService {
	public UserResultDto userRegister(UserDto userDto);
	
	public UserResultDto userUpdate(UserDto userDto);
//	public UserResultDto userUpdate(UserDto userDto, MultipartHttpServletRequest request);
	
	public UserResultDto userPasswordUpdate(UserDto userDto);

	public UserResultDto userDelete(String userId);


}