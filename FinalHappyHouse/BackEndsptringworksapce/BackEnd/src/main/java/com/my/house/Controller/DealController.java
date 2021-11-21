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
import com.my.house.dto.DealParamDto;
import com.my.house.dto.DealResultDto;
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
	@GetMapping(value = "/deals")
	public ResponseEntity<DealResultDto> boardList(DealParamDto boardParamDto) {

		DealResultDto boardResultDto;

		if (boardParamDto.getSearchWord().isEmpty()) {
			boardResultDto = service.dealList(boardParamDto);
		} else {
			boardResultDto = service.dealList(boardParamDto);
		}

		if (boardResultDto.getResult() == SUCCESS) {
			
			return new ResponseEntity<DealResultDto>(boardResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<DealResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	

	@GetMapping(value="/deals/{boardId}")
	public ResponseEntity<DealResultDto> boardDetail(@PathVariable int boardId, HttpSession session){

		DealParamDto boardParamDto = new DealParamDto();
		System.out.println("board detail serch " + boardId);
		boardParamDto.setBoardId(boardId);
		
		DealResultDto boardResultDto = service.dealDetail(boardParamDto);
		// 寃뚯떆湲� �옉�꽦�옄�� �쁽 �궗�슜�옄媛� �룞�씪
//		if( ((UserDto) session.getAttribute("userDto")).getUserSeq() == boardResultDto.getDto().getUserSeq() ) {
//			boardResultDto.setOwner(true);
//		}				
				
		if( boardResultDto.getResult() == SUCCESS ) {
			System.out.println("board detail " + boardResultDto.getDto().toString());
			return new ResponseEntity<DealResultDto>(boardResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<DealResultDto>(boardResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		
	}
	
	
	@GetMapping(value = "/deal/ListTotalCnt")
	private void ListTotalCnt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String searchWord = request.getParameter("searchWord");

		int totalCnt;
		totalCnt = service.ListTotalCnt(searchWord);

		response.setContentType("text/html; charset=utf-8");

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("totalCnt", totalCnt);
		String jsonStr = gson.toJson(jsonObject);
		response.getWriter().write(jsonStr);

		System.out.println("dealServlet ListTotalCnt totalCnt : " + totalCnt);

	}

}
