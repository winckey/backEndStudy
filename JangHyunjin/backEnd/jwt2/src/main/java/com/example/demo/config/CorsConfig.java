package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
public class CorsConfig {//크로스 오리진 설정

   @Bean
   public CorsFilter corsFilter() {//간단히 말하면 html 문서 상단에 서버의 데이터에 접근 할 수 있는 대상을 명시하는 것이다.
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowCredentials(true);// 내서버가 응답할때 json을 자바스크립트에서 처리할수있도록 설(jxas나 패치에서 처리가능)
      config.addAllowedOrigin("*"); // e.g. http://domain1.com//모든 ip에 응답허용
      config.addAllowedHeader("*");// 모든 헤더 응답 허용
      config.addAllowedMethod("*");//모든 메소드에 응답 허용

      source.registerCorsConfiguration("/api/**", config);
      return new CorsFilter(source);
   }

}