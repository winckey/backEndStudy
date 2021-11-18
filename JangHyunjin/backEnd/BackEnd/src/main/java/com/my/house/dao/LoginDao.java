package com.my.house.dao;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.UserDto;

@Mapper
public interface LoginDao {
	public UserDto login(String userEmail);
}
// @Mapper는 매퍼 등록을 위한 애너테이션으로 사용

//@Repository 어노테이션은 DAO에 특화된 어노테이션이다. 
//@Component 어노테이션을 써도 상관없지만, 
//DAO 클래스들에 @Repository 어노테이션을 사용함으로써 @Component 어노테션이 가진 특성과 함께, 
//DAO의 메소드에서 발생할 수 있는 unchecked 
//exception들을 스프링의 DataAccessException으로 처리할 수 있는 장점 또한 갖을 수 있다.

