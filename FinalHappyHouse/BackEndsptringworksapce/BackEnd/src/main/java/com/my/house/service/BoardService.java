package com.my.house.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dto.BoardDto;
import com.my.house.dto.BoardParamDto;
import com.my.house.dto.BoardResultDto;


public interface BoardService {
	
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);
	
	public BoardResultDto boardDelete(int boardId);
	
	public BoardResultDto boardUpdate(BoardDto dto, MultipartHttpServletRequest request);

	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);

	public BoardResultDto boardList(BoardParamDto boardParamDto);
	//public int boardListTotalCount();	
	public BoardResultDto boardListSearchWord(BoardParamDto boardParamDto);
	//public int boardListSearchWordTotalCount(boardParamDto boardParamDto);

}
