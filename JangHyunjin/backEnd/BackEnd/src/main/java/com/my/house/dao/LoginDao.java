package com.my.house.dao;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.UserDto;

@Mapper
public interface LoginDao {
	public UserDto login(String userEmail);
}
// @Mapper�� ���� ����� ���� �ֳ����̼����� ���

//@Repository ������̼��� DAO�� Ưȭ�� ������̼��̴�. 
//@Component ������̼��� �ᵵ ���������, 
//DAO Ŭ�����鿡 @Repository ������̼��� ��������ν� @Component ����׼��� ���� Ư���� �Բ�, 
//DAO�� �޼ҵ忡�� �߻��� �� �ִ� unchecked 
//exception���� �������� DataAccessException���� ó���� �� �ִ� ���� ���� ���� �� �ִ�.

