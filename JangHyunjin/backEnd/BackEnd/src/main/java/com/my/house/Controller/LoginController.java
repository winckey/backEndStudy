package com.my.house.Controller;

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
			methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
					    RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
	///프로토컬, 도메인, 포트가 모두 같을 시 동일 출처 정책을 따른다고 볼 수 있다.
	///ajax를 사용하는데 이 same-origin policy를 따르지 않을 경우, 오류가 나면서 api 사용이 거절된다.
	///보안 이슈 발생 --- 추가 공부
	
	@RestController // controller + requsetBody
//	/// @Bean
//	  @Bean은 개발자가 직접 제어가 불가능한 외부 라이브러리를 사용할 때 사용한다.
//	  @Configuration을 선언한 클래스 내부에서 사용해준다.
//	  즉, 개발자가 작성한 메소드를 통해 반환되는 객체를 Bean으로 만든다.
//	 @Component : scan-auto-detection과 dependency injection을 사용하기 위해서 사용되는 가장 기본 어노테이션이다.
//	 @Component는 개발자가 직접 작성한 Class를 Bean으로 등록 할 수 있게 만들어 준다.
//	 즉 개발자가 작성한 class를 Bean으로 만든다.

//  requsetBody : restApi를 사용하기 위한 어노테이션	
//  controller(여기서만 requestmapping사용가능) : @RequestMapping => urlmapping 에 사용	
	
	
// rest  : REST는 분산 시스템 설계를 위한 아키텍처 스타일이다.(여러 api에서 사용하기 위한 하나의 약속)
//		     어떤 다른 모듈 또는 애플리케이션들이라도 RESTful API를 통해 상호간에 통신
//REST의 구성 요소
//HTTP URI = 자원 => /bords
//HTTP Method = 행위 =>/get
//MIME Type = 표현 방식  => json/Content-type
	//ex)HTTP/1.1 200 OK
//		 Content-Type : application/json-patch+json
// uri 와 url
//	Uniform Resource Identifier, Uniform Resource Locator
//	REST에서는 모든 것을 Resource로 표현한다. 그리고 그 자원은 유일한 것을 나타낸다. Identifier, 식별자라는 것이다.
//	반면에 과거의 웹에서는 Identifier의 개념이 따로 필요없었다. html같은 파일들을 주고 받았기 때문에 파일의 위치를 가리키는 Locator를 썼다고 이해하면 된다.
//	URI가 파일뿐만 아니라 여러 자원들 까지도 포함하는 개념으로 이해할 수 있다.
	public class LoginController {
	
		@Autowired
		LoginService service;
		
//		(클라이언트에서 서버로) 요청본문 requestBody, 응답본문 responseBody
//		비동기 통신할때 사용
//		ResponseBody
//		Spring MVC의 컨트롤러에서는 데이터를 반환하기 위해 @ResponseBody 어노테이션을 활용해주어야 합니다. 이를 통해 Controller도 Json 형태로 데이터를 반환할 수 있습니다.
//		RestController = @Controller + @ResponseBody
//		RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것입니다. 
//		@Controller와는 다르게 @RestController는 리턴값에 자동으로 @ResponseBody가 붙게되어 별도 어노테이션을 명시해주지 않아도 HTTP 응답데이터(body)에 자바 객체가 매핑되어 전달 된다
//		@RequestBody
//		클라이언트에서 서버로 요청 본문을 자바 객체로 conversion : json형태의 객체를 넘겨받을때 사용
		
		// json request (body /payload)
		// request content type : application/json
		@PostMapping(value="/login")
		public ResponseEntity<UserDto> login(@RequestBody UserDto dto, HttpSession session){ // session
			//// responsebody -> 비동기 처리 할때 사용  [응답 본문], 자바객체를 http요청에 실어 보낼때 사용함
			// //requestBody -> 요청 본문을 자바 객체로 conversion : json형태의 객체를 넘겨받을때 사용
			
			UserDto userDto = service.login(dto);
			if( userDto != null ) {
				session.setAttribute("userDto", userDto);
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			}
			return new ResponseEntity<UserDto>(userDto, HttpStatus.NOT_FOUND);
		}
		
//		단점으로는 HTTP 규격 구성 요소 중 하나인 Header 에 대해서 유연하게 설정을 할 수 없다는 점입니다. 
//		또한 Status 도 메서드 밖에서 Annotation 을 사용하여 따로 설정을 해주어야한다는 점이 있습니다. 
//		이는 @ResponseBody 만 사용시에 별도의 뷰를 제공하지 않고, 데이터만 전송하는 형식이기 때문입니다.
//		이와 같은 점들을 해결해 줄 수 있는 것이 ResponseEntity 라는 객체
		
//@ResponseBody 와 달리 Annotation 이 아닌 객체로 사용이 됩니다. 즉, 응답으로 변환될 정보를 모두 담은 요소들을 객체로 만들어서 반환
		
//ResponseEntity 를 사용할 때, Constructor 를 사용하기보다는 Builder 를 활용하는 것을 권장하고 있습니다. 
//그 이유는 숫자로 된 상태 코드를 넣을 때, 잘못된 숫자를 넣을 수 있는 실수 때문
}


