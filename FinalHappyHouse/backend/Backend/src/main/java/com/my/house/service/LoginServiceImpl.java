package com.my.house.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.LoginDao;
import com.my.house.dto.UserDto;

// == @Componenet
@Service 
public class LoginServiceImpl implements LoginService {

	// service :타입을 맞춰주기 위해 존재함.. 
	// 어댑터 패턴을 공부해보시오!

	@Autowired
	LoginDao loginDao;
	
	@Override
	public UserDto login(UserDto dto) {
		
//		System.out.println("service : " + dto);
		UserDto userDto = loginDao.login(dto.getUserId());
		
		if( userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
			return userDto;
		}else {
			return null;
		}
	}
}
