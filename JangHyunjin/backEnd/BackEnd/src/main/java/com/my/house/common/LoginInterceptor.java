package com.my.house.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.my.house.dto.UserDto;


@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	//MVC Request Lifecycle https://velog.io/@damiano1027/Spring-Spring-MVC-Request-Lifecycle 참고
	//DispatcherServlet(web.xml) 을 두어 프론트 컨트롤러 패턴으로 디자인되었습니다.

	//URI 호출을 '가로채는' 역할을 합니다. 이를 이용하여 기존 컨트롤러의 로직을 수정하지 않고도, 사전이나 사후 제어가 가능
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			    //  	request : uri,헤더등을 객체로저장
			    //   	response : 리턴없이 실기만하면 전달할수 있다.
				//		**WAS가 웹브라우져로부터 Servlet요청을 받으면**
				//
				//		1. 요청을 받을 때 전달 받은 정보를 HttpServletRequest객체를 생성하여 저장
				//		2. 웹브라우져에게 응답을 돌려줄 HttpServletResponse객체를 생성(빈 객체)
				//		3. 생성된 HttpServletRequest(정보가 저장된)와 HttpServletResponse(비어 있는)를 Servlet에게 전달
				//
				//		**HttpServletRequest**
				//
				//		1. Http프로토콜의 request 정보를 서블릿에게 전달하기 위한 목적으로 사용
				//		2. Header정보, Parameter, Cookie, URI, URL 등의 정보를 읽어들이는 메소드를 가진 클래스
				//		3. Body의 Stream을 읽어들이는 메소드를 가지고 있음
				//
				//		**HttpServletResponse**
				//
				//		1. Servlet은 HttpServletResponse객체에 Content Type, 응답코드, 응답 메시지등을 담아서 전송함
	
		//preHandle 지정된 컨트롤러의 동작 이전에 수행할 동작 (사전 제어).
		
		System.out.println("login interceptor>>>> " + request.getRequestURI() );
//		request.getRequestURI() 함수 = 프로젝트 + 파일경로까지 가져옵니다.
//		예)  http://localhost:8080/project/list.jsp
//		[return]        /project/list.jsp  를 가져옵니다 ㅎ
		
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        
        // CORS 의 put, delete 에서 options request 가 브라우저에 의해서 발생되는 데 이를 무조건 수락
        // 더이상 sendredirect 하지 않음
        if( request.getMethod().equals("OPTIONS")) {//OPtions 는 브라우저가 알아서 보낸다 => cors문제가 생겼을경우 이걸보넴
			// 에러처리를 위한 if문
        	System.out.println("login interceptor CORS OPTIONS : "+ request.getMethod());
        	return true;
        }
        
        
        if( userDto == null ) {
        	
			Gson gson = new Gson();
        	
			JsonObject jsonObject = new JsonObject();
			//jsonObject : string을 json객체로 전환
			jsonObject.addProperty("result", "login");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);

        	return false;
        }

        return true;
    }
}
