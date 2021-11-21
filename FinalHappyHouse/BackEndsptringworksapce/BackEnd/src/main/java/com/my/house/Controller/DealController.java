package com.my.house.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;
import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;
import com.my.house.service.DealService;
@CrossOrigin(///// 媛쒕컻�슜�룄濡� �궗�슜�븯�뒗嫄곗엫
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				    RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class DealController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// DealServiceImpl 媛앹껜
	@Autowired
	DealService service;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	@GetMapping(value = "/houses")
	public ResponseEntity<HouseResultDto> houseList(HouseParamDto houseParamDto) {

		HouseResultDto houseResultDto;

		if (houseParamDto.getSearchWord().isEmpty()) {
			houseResultDto = service.dealList(houseParamDto);
		} else {
			houseResultDto = service.dealList(houseParamDto);
		}

		if (houseResultDto.getResult() == SUCCESS) {
			
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
