package com.example.demo.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


// .loginProcessingUrl("/login") 요청이 오면 자동으로 userDetailsService 타입으로 ioc 되어있는
// 이클래스 의 loadUserByUsername이 자동으로 호출이됨 (규칙임)
// 그후 넘어오는 username을 찾음



@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//값매칭을 위해 username으로 맞추어야 함 싫다면 시큐리티 설정에서 userNameParam으로 바꾸어 주어야함
		User user = userRepository.findByUsername(username);// 로그인 검사 과정
		if(user == null) {
			return null;
		}
		return new PrincipalDetails(user);// 아이디가 있다면 UserDetails을 상속한 PrincipalDetails호출
										  // 이게 리턴이 어디로 되느냐 authentication으로 리턴된다
										  // 그리고 이걸 sesstion이 받아서 관리한다.
	}

}