package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
				   // aop나 세션을 만들어 인증할수 있지만 프레임 워크를 이용하여 간단하게 작업할수있다
//filter 기반으로 동작합니다.(체인방식으로 여러개 추가 가능)
//어노테이션을 통해 간단한 설정이 가능합니다
//기본적으로는 세션 & 쿠키방식으로 인증됩니다.
//dispatcher servlet 이전에 처리됩니다.
//https://frozenpond.tistory.com/94


@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{// <=스프링 시큐리티 필터 

	
	@Bean  // 해당 메소드의 리턴되는 오브젝트를 ioc로 등록
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated() // 유저 요청
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") // 매니저 요청
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")// 어드민요청
		.anyRequest().permitAll()// 다른 요청은 모두허용
		.and()
		.formLogin()//위에 걸릴경우
		.loginPage("/loginForm")//로그인페이지로 지정한 "/login"으로 모두 이동해라
		.loginProcessingUrl("/login") // login 주소가 호출되면 시큐리니까 대시처리해줌 // 컨트롤러에 만들 필요가 없음"/login" 
		.defaultSuccessUrl("/");// 로그인이 완료되면 이동할 곳
	}
	
}
