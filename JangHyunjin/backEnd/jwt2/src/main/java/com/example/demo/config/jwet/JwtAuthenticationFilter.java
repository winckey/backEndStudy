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





//스프링 시큐리티에서 username password인증 필터가 있음 -> /login 요청시 자동으로 호출
//이제 안쓸꺼임 
//formlogin.disable
//이필터를 시큐리티에 등록해줄꺼임[커스텀]
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	// private final AuthenticationManager authenticationManager;
	// 롬복이 지금 안댐
	private AuthenticationManager authenticationManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	
	
	
	// Authentication 객체 만들어서 리턴 => 의존 : AuthenticationManager
	// 인증 요청시에 실행되는 함수 => /login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		
		// 1. 아이디 비번 받아서 확인
		// 2. 정상 로그인시도인지 확인
		// 3. anthenticationManger 로 로그인 시도하면 principaldetailsService가 호출 => 로드 유저네임 자동 실행
		
		
		// 4 세션에 principaldetails 를 담아 보냄(권한 관리를 위해서)
		// 5. jwt 토큰을 만들어 응답
		
		
		System.out.println("JwtAuthenticationFilter : 진입");
		
		// request에 있는 username과 password를 파싱해서 자바 Object로 받기
		ObjectMapper om = new ObjectMapper();

		try {
			User user  = om.readValue(request.getInputStream(), User.class);
			
			
			UsernamePasswordAuthenticationToken token 
				= new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			// 원래 form에서 해주지만 커스텀을 위해 직접 만든다.
			
			
			Authentication authentication = authenticationManager.authenticate(token);
			//principaldetailsservice에 로브바이 유저내임 실행 
			//토큰을 매니저에 너어서 던지면 인증을 해준다.
			//여기에는 내가 로그인한 정보가 담긴다.
			return authentication;
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		// authenticate() 함수가 호출 되면 인증 프로바이더가 유저 디테일 서비스의
		// loadUserByUsername(토큰의 첫번째 파라메터) 를 호출하고
		// UserDetails를 리턴받아서 토큰의 두번째 파라메터(credential)과
		// UserDetails(DB값)의 getPassword()함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.
		
		// Tip: 인증 프로바이더의 디폴트 서비스는 UserDetailsService 타입
		// Tip: 인증 프로바이더의 디폴트 암호화 방식은 BCryptPasswordEncoder
		// 결론은 인증 프로바이더에게 알려줄 필요가 없음.
	
		return null;
	}

	// 위에께 실행되고 이게 실행됨
	// JWT Token 생성해서 response에 담아주기
		@Override
		protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
				Authentication authResult) throws IOException, ServletException {
			// 이게 실행되면 인증이 완료되었다는 뜻!
			PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();
			
			String jwtToken = JWT.create()
					.withSubject(principalDetailis.getUsername())// 토큰이름
					.withExpiresAt(new Date(System.currentTimeMillis()+1000000))// 만료시간
					.withClaim("id", principalDetailis.getUser().getId())//아무 키밸류값
					.withClaim("username", principalDetailis.getUser().getUsername())//아무 키밸류값
					.sign(Algorithm.HMAC512("cos"));/// 고유키
			
			response.addHeader("Authorization", "Bearer "+jwtToken);
		}
	
}