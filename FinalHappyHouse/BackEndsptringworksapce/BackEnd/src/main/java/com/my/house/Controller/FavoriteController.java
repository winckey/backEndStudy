package com.my.house.Controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.FavoriteResultDto;

@CrossOrigin(/////
		origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
				RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
				RequestMethod.OPTIONS })
@RestController
public class FavoriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	FavoriteService favoriteService;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;


	@PostMapping(value = "/addFavorite")
	public ResponseEntity<FavoriteResultDto> agentList(int userNo , int agentNo) {

		FavoriteResultDto favoriteResultDto;

		
		favoriteResultDto = favoriteService.agentList(userNo);
		
		if (favoriteResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/removeFavorite")
	public ResponseEntity<FavoriteResultDto> agentDetail(int userNo , int agentNo) {

		FavoriteResultDto favoriteResultDto;

		
		favoriteResultDto = favoriteService.agentDetail(agentNo);
		
		if (favoriteResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
