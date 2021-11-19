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


	// preHandler 컨트롤러가기 전  
	// postHandler  컨트롤러 후 
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// request 요청 , response 응답
		// 객체나 헤더가 담겨있음
		// 새로운 요청이 오기전까진 세션에유지됨 

		System.out.println(">>>>>>>>>>>> "+request.getRequestURI() );
		//세션을 통해서 가져와서 생성한 dto값이 있으면 넘어가고 아니면 로그인페이지로 redirect 시킴
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        //CORS의 put,delete 에서 options request가 브라우저에 의해서 발생되는 데 이를 무조건 수락
        if( request.getMethod().equals("OPTIONS")) {
        	return true;
        }
        if( userDto == null ) {
        	        	
				Gson gson = new Gson();

				JsonObject jsonObject = new JsonObject();
				jsonObject.addProperty("result", "login");
				
				String jsonStr = gson.toJson(jsonObject);
				response.getWriter().write(jsonStr);
		

        	return false;
        }

        return true;
    }
}
