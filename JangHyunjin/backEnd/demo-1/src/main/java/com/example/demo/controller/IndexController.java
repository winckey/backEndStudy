package com.example.demo.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Controller
public class IndexController {


	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	

	@GetMapping({ "", "/" })
	public @ResponseBody String index() {
		return "인덱스 페이지입니다.";
	}

	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "어드민 페이지입니다.";
	}
	@GetMapping("/user")
	public @ResponseBody String user() {
		return "user 페이지입니다.";
	}
	//@PostAuthorize("hasRole('ROLE_MANAGER')")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	@Secured("ROLE_MANAGER")
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "매니저 페이지입니다.";
	}



	@GetMapping("/joinForm")
	public String join() {
		return "joinForm";
	}
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@PostMapping("/joinProc")
	public String joinProc(User user) {
		System.out.println("회원가입 진행 : " + user);
		user.setRole("ROLE_USER");
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		userRepository.save(user);// 이렇게 하면 비밀번호 암호화가 안댐
		
		return "redirect:/loginForm";// loginForm 함수 호출
	}
}