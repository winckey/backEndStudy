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
			throws IOException, ServletException {//FilterChain ���͵��� ���� ������� ������

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getMethod().equals("POST")) {// �޼ҵ尡 post ��� �ΰ��

			String headerAuth = req.getHeader("Authorization"); // ����� Authorization�� �޾ƿ´�.
			System.out.println(headerAuth);
		}
		System.out.println("����1");
		chain.doFilter(req, res);

	}

}
