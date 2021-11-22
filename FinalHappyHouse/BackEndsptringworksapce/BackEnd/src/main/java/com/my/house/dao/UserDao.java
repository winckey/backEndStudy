package com.my.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.UserDto;
import com.my.house.dto.UserFileDto;

@Mapper
public interface UserDao {
	public int userRegister(UserDto userDto);
	public int userUpdate(UserDto userDto);
	public int userPasswordUpdate(UserDto userDto);
	public int userDelete(String userId);

	public int userFileDelete(String userId);
	public List<String> userFileUrlDeleteList(String userId);

	public int userFileInsert(UserFileDto dto);

}