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
@EnableWebSecurity // ��ť��Ƽ Ȱ��ȭ -> �⺻ ������ ����ü�ο� ���
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
		.addFilter(corsConfig.corsFilter())//��� ũ�ν����������� �����// �츮�� ���� ���ͺ��� ��ť��Ƽ�� �켱���� �����
		.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//������ ������� �ʰڴ�
		.and()
		.formLogin().disable()// ���±� �α���ȭ�� x
		.httpBasic().disable()// ������ user������Ʈ�� �����ϰ� ����id ��ȯ Ŭ���ƾ�Ʈ�� �̰� ��Ű�� ����
								// ���򿡴� http ��Ű�� �������� ����ϰ� ���� -> ���Ͽ��� ���� fetch.then�� �̿��ؼ� ������ �ð�� �ź��Ѵ�.
								// ��Ű�� ���� ��ó ��å�� ���� ������ �̷��� �����
								// bearer��� Ȱ��ȭ -> ��ū�� �����Ű�� ���
								// ��ū�� ��ȣȭ / ��ȿ�ð��� �ֱ⋚���� ���� ������
		.addFilter(new JwtAuthenticationFilter(authenticationManager()))// authenticationManger �� �Ķ���ͷ� �����[�� ��ť��Ƽ�� ����������]
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



