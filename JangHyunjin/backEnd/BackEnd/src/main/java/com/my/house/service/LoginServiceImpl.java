package com.my.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.LoginDao;
import com.my.house.dto.UserDto;


@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;
	
	@Override
	public UserDto login(UserDto dto) {
		
		UserDto userDto = loginDao.login(dto.getUserId());
		System.out.println("userservice login before dto : " + userDto);
		if( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
			System.out.println("userservice login dto : " + userDto);
			return userDto;
		}else {
			return null;
		}
	}
}