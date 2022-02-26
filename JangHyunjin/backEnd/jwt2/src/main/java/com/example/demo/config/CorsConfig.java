package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
public class CorsConfig {//ũ�ν� ������ ����

   @Bean
   public CorsFilter corsFilter() {//������ ���ϸ� html ���� ��ܿ� ������ �����Ϳ� ���� �� �� �ִ� ����� ����ϴ� ���̴�.
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);// �������� �����Ҷ� json�� �ڹٽ�ũ��Ʈ���� ó���Ҽ��ֵ��� ��(jxas�� ��ġ���� ó������)
      config.addAllowedOrigin("*"); // e.g. http://domain1.com//��� ip�� �������
      config.addAllowedHeader("*");// ��� ��� ���� ���
      config.addAllowedMethod("*");//��� �޼ҵ忡 ���� ���

      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}