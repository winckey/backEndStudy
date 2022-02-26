package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter1 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {//FilterChain 필터들의 집합 순서대로 동작함

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getMethod().equals("POST")) {// 메소드가 post 방식 인경우

			String headerAuth = req.getHeader("Authorization"); // 헤더의 Authorization를 받아온다.
			System.out.println(headerAuth);
		}
		System.out.println("필터1");
		chain.doFilter(req, res);

	}

}
