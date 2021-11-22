package com.my.house.Controller;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;
import com.my.house.service.HouseService;

@CrossOrigin(/////
		origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
				RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
				RequestMethod.OPTIONS })
@RestController
public class HouseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	HouseService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

	@GetMapping(value = "/houses")
	public ResponseEntity<HouseResultDto> houseList(HouseParamDto houseParamDto) {

		HouseResultDto houseResultDto;

		if (houseParamDto.getSearchWord().isEmpty()) {
			houseResultDto = service.houseList(houseParamDto);
		} else {
			houseResultDto = service.houseListSearchWord(houseParamDto);
		}

		if (houseResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/houses/{houseNo}")
	public ResponseEntity<HouseResultDto> houseDetail(@PathVariable int houseNo) {

		
		HouseParamDto houseParamDto = new HouseParamDto();
		houseParamDto.setHouseNo(houseNo);

		HouseResultDto houseResultDto = service.dealDetail(houseParamDto);
	
		if (houseResultDto.getResult() == SUCCESS) {
			System.out.println("-------houseDetail dto : " + houseResultDto.getResult());
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<HouseResultDto>(houseResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
