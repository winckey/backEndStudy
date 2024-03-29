package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
// @CrossOrigin  // CORS 허용 
public class RestApiController {

	// 모든 사람이 접근 가능
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}

	// 모든 사람이 접근 가능
	@PostMapping("token")
	public String token() {
		return "<h1>token</h1>";
	}

	// 모든 사람이 접근 가능
	@GetMapping("/")
	public String homeInit() {
		return "<h1>home</h1>";
	}

	// Tip : JWT를 사용하면 UserDetailsService를 호출하지 않기 때문에 @AuthenticationPrincipal 사용
	// 불가능.
	// 왜냐하면 @AuthenticationPrincipal은 UserDetailsService에서 리턴될 때 만들어지기 때문이다.

	// 매니저 혹은 어드민이 접근 가능
	@GetMapping("manager/reports")
	public String reports() {
		return "<h1>reports</h1>";
	}

}
