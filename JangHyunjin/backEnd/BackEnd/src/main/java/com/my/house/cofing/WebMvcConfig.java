package com.my.house.cofing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.my.house.common.LoginInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer{/// WebMvcConfigurer : 인터페이스등을 동록(커스텀)등을 위한 config
//	@EnableWebMvc는 DispatcherServlet의 기본 전략을 사용하는 것이 아니라 DelegatingWebMvcConfiguration을 import하는데, import하는 설정은 Delegation 구조로 되어있다.
//	Delegation 구조로 되어 있는 이유는, 확장성을 좋게하기 위함이다.
//	이로 인해, 우리가 원하는 대로 쉽게 커스텀 할 수 있는 것이다.
	//이 때, 커스텀 하기 위해서는 우리는 인터페이스를 확장해야 하는데, 이 때 필요한 인터페이스가 WebMvcConfigurer이다.
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) { ///// intercetor 등록 함수
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns(/// 이하의 경로로 요청시 interceptor미적용
				"/", 
				"/index.html",
				"favicon.ico",
				"/css/**",
				"/js/**",
				"/login/**",
				"/logout/**",
				"/register/**"
				);
	}
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("/static/login.html");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }
}
