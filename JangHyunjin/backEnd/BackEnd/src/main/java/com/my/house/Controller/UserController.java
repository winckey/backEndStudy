package com.my.house.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;
import com.my.house.service.UserService;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
		RequestMethod.OPTIONS })

@RestController
public class UserController {

	@Autowired
	UserService userService;

	private static final int SUCCESS = 1;

	@PostMapping(value = "/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto dto, HttpSession session) {

		UserResultDto userResultDto = userService.userRegister(dto);

		if (userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/user/mypage")
	public UserDto mypage(UserDto dto, HttpSession session) {

		UserDto userDto = (UserDto) session.getAttribute("userDto");

		return userDto;
	}

}