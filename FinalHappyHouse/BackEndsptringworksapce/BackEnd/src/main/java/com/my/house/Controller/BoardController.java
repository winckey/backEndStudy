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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dto.BoardDto;
import com.my.house.dto.BoardParamDto;
import com.my.house.dto.BoardResultDto;
import com.my.house.dto.UserDto;
import com.my.house.service.BoardService;


@CrossOrigin(///// 개발용도로 사용하는거임
		origins = "http://localhost:5500",
		allowCredentials = "true",
		allowedHeaders = "*",
		methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,
				    RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS })
@RestController
public class BoardController {

	@Autowired
	BoardService service;
	
	private static final int SUCCESS = 1;
	
	@GetMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardList(BoardParamDto boardParamDto){
		
		BoardResultDto boardResultDto;
		
		if( boardParamDto.getSearchWord().isEmpty() ) {
			boardResultDto = service.boardList(boardParamDto);
		}else {
			boardResultDto = service.boardListSearchWord(boardParamDto);
		}
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping(value="/boards/{boardId}")
	public ResponseEntity<BoardResultDto> boardDetail(@PathVariable int boardId, HttpSession session){

		
		
		BoardParamDto boardParamDto = new BoardParamDto();
		boardParamDto.setBoardId(boardId);
			
		
		System.out.println("boardDetail : " + boardId);
		BoardResultDto boardResultDto = service.boardDetail(boardParamDto);
		
		System.out.println("1. board Detail : " + boardResultDto );
		
		// 게시글 작성자와 현 사용자가 동일
		if( ((UserDto) session.getAttribute("userDto")).getUserNo() == boardResultDto.getDto().getUserNo() ) {
			boardResultDto.getDto().setSameUser(true);
		}	
				
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	@PostMapping(value="/boards")
	public ResponseEntity<BoardResultDto> boardInsert(
			BoardDto boardDto, 
			MultipartHttpServletRequest request) {
		
		boardDto.setUserNo( ((UserDto) request.getSession().getAttribute("userDto")).getUserNo());
		BoardResultDto boardResultDto = service.boardInsert(boardDto, request);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
	
	// PUT + multipart/form-data (X)
	// In RESTful,
	// PUT & DELETE methods are defined to be idempotent
	
	@PostMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardUpdate(
			BoardDto boardDto, 
			MultipartHttpServletRequest request){

		BoardResultDto boardResultDto = service.boardUpdate(boardDto, request);
		boardDto.setUserNo( ((UserDto) request.getSession().getAttribute("userDto")).getUserNo());
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@DeleteMapping(value="/boards/{boardId}") 
	public ResponseEntity<BoardResultDto> boardDelete(@PathVariable(value="boardId") int boardId){
		BoardResultDto boardResultDto = service.boardDelete(boardId);
		
		if( boardResultDto.getResult() == SUCCESS ) {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<BoardResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		 
	}
}
