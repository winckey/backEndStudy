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
// @CrossOrigin  // CORS ��� 
public class RestApiController {

	// ��� ����� ���� ����
	@GetMapping("home")
	public String home() {
		return "<h1>home</h1>";
	}

	// ��� ����� ���� ����
	@PostMapping("token")
	public String token() {
		return "<h1>token</h1>";
	}

	// ��� ����� ���� ����
	@GetMapping("/")
	public String homeInit() {
		return "<h1>home</h1>";
	}

	// Tip : JWT�� ����ϸ� UserDetailsService�� ȣ������ �ʱ� ������ @AuthenticationPrincipal ���
	// �Ұ���.
	// �ֳ��ϸ� @AuthenticationPrincipal�� UserDetailsService���� ���ϵ� �� ��������� �����̴�.

	// �Ŵ��� Ȥ�� ������ ���� ����
	@GetMapping("manager/reports")
	public String reports() {
		return "<h1>reports</h1>";
	}

}
