package com.my.house.Controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.house.dto.AgentResultDto;
import com.my.house.service.AgentService;

@CrossOrigin(/////
		origins = "http://localhost:5500", allowCredentials = "true", allowedHeaders = "*", methods = {
				RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.HEAD,
				RequestMethod.OPTIONS })
@RestController
public class AgentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	AgentService service;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;

//	@GetMapping(value = "/agents")
//	public ResponseEntity<AgentResultDto> agentList2() {
//
//		AgentResultDto agentResultDto;
//
//		
//		agentResultDto = service.agentList();
//		
//		if (agentResultDto.getResult() == SUCCESS) {
//
//			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

	@GetMapping(value = "/agents/list/{userNo}")
	public ResponseEntity<AgentResultDto> agentList(@PathVariable int userNo) {

		AgentResultDto agentResultDto;

		
		agentResultDto = service.agentList(userNo);
		
		if (agentResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/agents/{agentNo}")
	public ResponseEntity<AgentResultDto> agentDetail(@PathVariable int agentNo) {

		AgentResultDto agentResultDto;

		
		agentResultDto = service.agentDetail(agentNo);
		
		if (agentResultDto.getResult() == SUCCESS) {

			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<AgentResultDto>(agentResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
