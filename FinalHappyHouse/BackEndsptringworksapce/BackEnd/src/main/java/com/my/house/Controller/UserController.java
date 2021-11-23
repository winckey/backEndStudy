package com.my.house.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dto.AgentResultDto;
import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;
import com.my.house.service.UserService;

@CrossOrigin(
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				    RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })


@RestController
public class UserController {

	@Autowired
	UserService userService; 
	
	private static final int SUCCESS = 1;
	
	@PostMapping(value="/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto dto, HttpSession session){
	
		
		UserResultDto userResultDto = userService.userRegister(dto);
		
		if( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/user/mypage")
	public UserDto mypage(UserDto dto, HttpSession session) {
		 
		UserDto userDto = (UserDto) session.getAttribute("userDto")  ;
		
		return userDto;
	}
	
	
	@PutMapping(value="/passwordUpdate")
	public ResponseEntity<UserResultDto> passwordUpdate(@RequestBody UserDto dto, HttpSession session){

		
		UserResultDto userResultDto = userService.userPasswordUpdate(dto);
		
		
		if( userResultDto.getResult() == SUCCESS ) {
			session.invalidate();
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value="editProfile")
	public ResponseEntity<UserResultDto> updateProfile(UserDto userDto, 
			MultipartHttpServletRequest request) {
		System.out.println("controller , before service"  + userDto);
		
		UserResultDto userResultDto = userService.userUpdate(userDto , request);
		System.out.println("controller , after service");
		
		if( userResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 파라미터에 어노테이션 붙여야하나?
	@GetMapping(value = "/user/favoriteAgents")
	public ResponseEntity<UserResultDto> userFavoriteAgentList(int userNo) {

		UserResultDto userFavoriteResultDto = userService.userFavoriteAgentList(userNo);
		
		if (userFavoriteResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<UserResultDto>(userFavoriteResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userFavoriteResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@DeleteMapping(value="/user/delete")
	public ResponseEntity<UserResultDto> delete(UserDto dto, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto")  ;
		UserResultDto userResultDto = userService.userDelete(userDto.getUserId());
		
		if( userResultDto.getResult() == SUCCESS ) {
			session.invalidate();
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}}
