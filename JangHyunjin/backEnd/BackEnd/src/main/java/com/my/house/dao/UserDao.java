package com.my.house.dao;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.UserDto;

@Mapper
public interface UserDao {
	public int userRegister(UserDto userDto);
}
