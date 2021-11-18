package com.my.house.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.UserDto;
import com.my.house.service.LoginService;

	@CrossOrigin(
			origins = "http://localhost:5500",
			allowCredentials = "true",
			allowedHeaders = "*",
			methods = { RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,
					RequestMethod.DELETE, RequestMethod.HEAD,RequestMethod.OPTIONS})
	// 프로토콜, 도메인 , 포트가 다를 경우 이렇게 임의로 지정해서 허용 해줘야 함! 
	// 보안 이슈가 있을 수 있기 때문
	// 나중에 build시 지워도 될듯 
	
	
	
	// @controller + @responseBody 
	// @Controller - request Mapping 을 쓰기 위해 
	// responsebody -> 비동기 처리 할때 사용  [응답 본문], 자바객체를 http요청에 실어 보낼때 사용함
	@RestController
	public class LoginController {
	
		@Autowired
		//싱글톤 패턴으로 자동생성
		LoginService service;
		
		
		@PostMapping(value="/login")
		//requestBody -> 요청 본문을 자바 객체로 conversion : json형태의 객체를 넘겨받을때 사용
		public ResponseEntity<UserDto> login(@RequestBody UserDto dto, HttpSession session){
			
			UserDto userDto = service.login(dto);
			if( userDto != null ) {
				session.setAttribute("userDto", userDto);
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			}
			return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_FOUND);
		}
//      단점으로는 HTTP 규격 구성 요소 중 하나인 Header 에 대해서 유연하게 설정을 할 수 없다는 점입니다. 
//      또한 Status 도 메서드 밖에서 Annotation 을 사용하여 따로 설정을 해주어야한다는 점이 있습니다. 
//      이는 @ResponseBody 만 사용시에 별도의 뷰를 제공하지 않고, 데이터만 전송하는 형식이기 때문입니다.
//      이와 같은 점들을 해결해 줄 수 있는 것이 ResponseEntity 라는 객체
      
//@ResponseBody 와 달리 Annotation 이 아닌 객체로 사용이 됩니다. 즉, 응답으로 변환될 정보를 모두 담은 요소들을 객체로 만들어서 반환
      
//ResponseEntity 를 사용할 때, Constructor 를 사용하기보다는 Builder 를 활용하는 것을 권장하고 있습니다. 
//그 이유는 숫자로 된 상태 코드를 넣을 때, 잘못된 숫자를 넣을 수 있는 실수 때문
	}


