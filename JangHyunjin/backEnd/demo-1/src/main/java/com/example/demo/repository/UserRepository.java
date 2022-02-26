package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;



// JpaRepository �� ����ϸ� �ڵ� ������Ʈ ��ĵ��.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	// Jpa Naming ����
	// SELECT * FROM user WHERE username = 1?
	User findByUsername(String username);
	// SELECT * FROM user WHERE username = 1? AND password = 2?
	// User findByUsernameAndPassword(String username, String password);
	
	// @Query(value = "select * from user", nativeQuery = true)
	// User find�������();
}