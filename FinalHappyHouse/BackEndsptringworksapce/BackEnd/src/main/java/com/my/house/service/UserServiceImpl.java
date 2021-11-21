package com.my.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.UserDao;
import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if( userDao.userRegister(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(String userId) {
		UserResultDto userResultDto = new UserResultDto();

		if( userDao.userDelete(userId) == 1 ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);

		}
		return userResultDto;
	}

	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		
		if( userDao.userUpdate(userDto) == 1 ) {
			
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);

		}
	
		
		return userResultDto;
	}
}
