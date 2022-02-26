package com.example.demo.config;


import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.config.jwet.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity // 시큐리티 활성화 -> 기본 스프링 필터체인에 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter{	
	
	@Autowired
	private CorsConfig corsConfig;
	
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilter(corsConfig.corsFilter())//모든 크로스오리진에서 벗어난다// 우리가 만든 필터보다 시큐리티가 우선으로 적용됨
		.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션을 사용하지 않겠다
		.and()
		.formLogin().disable()// 폼태그 로그인화면 x
		.httpBasic().disable()// 세션은 user오브젝트를 저장하고 세션id 반환 클리아언트는 이걸 쿠키에 저장
								// 요즘에는 http 쿠키만 서버에서 허용하고 있음 -> 파일에서 직접 fetch.then를 이용해서 강제로 올경우 거부한다.
								// 쿠키는 동일 출처 정책을 쓰기 떄문에 이렇게 사용함
								// bearer방식 활성화 -> 토큰을 노출시키는 방법
								// 토큰은 암호화 / 유효시간이 있기떄문에 비교적 안전함
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))// authenticationManger 를 파라미터로 줘야함[웹 시큐리티가 가지고있음]
		.authorizeRequests()
		.antMatchers("/api/vi/user/**")
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/vi/manager/**")
		.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		.antMatchers("/api/vi/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();
		
		
	}
}



