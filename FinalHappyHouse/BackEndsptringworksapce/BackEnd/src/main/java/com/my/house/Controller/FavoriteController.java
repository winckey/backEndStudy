package com.my.house.Controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.FavoriteResultDto;
import com.my.house.service.FavoriteService;

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


	@PostMapping(value = "/addFavorite/{userNo}/{agentNo}")
	public ResponseEntity<FavoriteResultDto> addFavorite(@PathVariable int userNo ,@PathVariable int agentNo) {

		FavoriteResultDto favoriteResultDto;

		System.out.println(userNo);
		favoriteResultDto = favoriteService.addFavorite(userNo, agentNo);
		
		if (favoriteResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/removeFavorite/{userNo}/{agentNo}")
	public ResponseEntity<FavoriteResultDto> removeFavorite(@PathVariable int userNo ,@PathVariable int agentNo) {

		FavoriteResultDto favoriteResultDto;

		
		favoriteResultDto = favoriteService.removeFavorite(userNo, agentNo);
		
		if (favoriteResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<FavoriteResultDto>(favoriteResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
