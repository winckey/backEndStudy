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
@EnableWebSecurity // ������ ��ť��Ƽ ���Ͱ� ������ ����ü�ο� ����� �ȴ�
				   // aop�� ������ ����� �����Ҽ� ������ ������ ��ũ�� �̿��Ͽ� �����ϰ� �۾��Ҽ��ִ�
//filter ������� �����մϴ�.(ü�ι������ ������ �߰� ����)
//������̼��� ���� ������ ������ �����մϴ�
//�⺻�����δ� ���� & ��Ű������� �����˴ϴ�.
//dispatcher servlet ������ ó���˴ϴ�.
//https://frozenpond.tistory.com/94


@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{// <=������ ��ť��Ƽ ���� 

	
	@Bean  // �ش� �޼ҵ��� ���ϵǴ� ������Ʈ�� ioc�� ���
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		http.authorizeRequests()
		.antMatchers("/user/**").authenticated() // ���� ��û
		.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") // �Ŵ��� ��û
		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")// ���ο�û
		.anyRequest().permitAll()// �ٸ� ��û�� ������
		.and()
		.formLogin()//���� �ɸ����
		.loginPage("/loginForm")//�α����������� ������ "/login"���� ��� �̵��ض�
		.loginProcessingUrl("/login") // login �ּҰ� ȣ��Ǹ� ��ť���ϱ� ���ó������ // ��Ʈ�ѷ��� ���� �ʿ䰡 ����"/login" 
		.defaultSuccessUrl("/");// �α����� �Ϸ�Ǹ� �̵��� ��
	}
	
}
