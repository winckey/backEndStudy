package com.example.demo.config.jwet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;





//������ ��ť��Ƽ���� username password���� ���Ͱ� ���� -> /login ��û�� �ڵ����� ȣ��
//���� �Ⱦ����� 
//formlogin.disable
//�����͸� ��ť��Ƽ�� ������ٲ���[Ŀ����]
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	// private final AuthenticationManager authenticationManager;
	// �Һ��� ���� �ȴ�
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	
	
	
	// Authentication ��ü ���� ���� => ���� : AuthenticationManager
	// ���� ��û�ÿ� ����Ǵ� �Լ� => /login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		
		// 1. ���̵� ��� �޾Ƽ� Ȯ��
		// 2. ���� �α��νõ����� Ȯ��
		// 3. anthenticationManger �� �α��� �õ��ϸ� principaldetailsService�� ȣ�� => �ε� �������� �ڵ� ����
		
		
		// 4 ���ǿ� principaldetails �� ��� ����(���� ������ ���ؼ�)
		// 5. jwt ��ū�� ����� ����
		
		
		System.out.println("JwtAuthenticationFilter : ����");
		
		// request�� �ִ� username�� password�� �Ľ��ؼ� �ڹ� Object�� �ޱ�
		ObjectMapper om = new ObjectMapper();

		try {
			User user  = om.readValue(request.getInputStream(), User.class);
			
			
			UsernamePasswordAuthenticationToken token 
				= new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			// ���� form���� �������� Ŀ������ ���� ���� �����.
			
			
			Authentication authentication = authenticationManager.authenticate(token);
			//principaldetailsservice�� �κ���� �������� ���� 
			//��ū�� �Ŵ����� �ʾ ������ ������ ���ش�.
			//���⿡�� ���� �α����� ������ ����.
			return authentication;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// authenticate() �Լ��� ȣ�� �Ǹ� ���� ���ι��̴��� ���� ������ ������
		// loadUserByUsername(��ū�� ù��° �Ķ����) �� ȣ���ϰ�
		// UserDetails�� ���Ϲ޾Ƽ� ��ū�� �ι�° �Ķ����(credential)��
		// UserDetails(DB��)�� getPassword()�Լ��� ���ؼ� �����ϸ�
		// Authentication ��ü�� ���� ����ü������ �������ش�.
		
		// Tip: ���� ���ι��̴��� ����Ʈ ���񽺴� UserDetailsService Ÿ��
		// Tip: ���� ���ι��̴��� ����Ʈ ��ȣȭ ����� BCryptPasswordEncoder
		// ����� ���� ���ι��̴����� �˷��� �ʿ䰡 ����.
	
		return null;
	}

	// ������ ����ǰ� �̰� �����
	// JWT Token �����ؼ� response�� ����ֱ�
		@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult) throws IOException, ServletException {
			// �̰� ����Ǹ� ������ �Ϸ�Ǿ��ٴ� ��!
			PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();
			
			String jwtToken = JWT.create()
					.withSubject(principalDetailis.getUsername())// ��ū�̸�
					.withExpiresAt(new Date(System.currentTimeMillis()+1000000))// ����ð�
					.withClaim("id", principalDetailis.getUser().getId())//�ƹ� Ű�����
					.withClaim("username", principalDetailis.getUser().getUsername())//�ƹ� Ű�����
					.sign(Algorithm.HMAC512("cos"));/// ����Ű
			
			response.addHeader("Authorization", "Bearer "+jwtToken);
		}
	
}