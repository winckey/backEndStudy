package com.my.house.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dto.BoardDto;
import com.my.house.dto.BoardParamDto;
import com.my.house.dto.BoardResultDto;

public interface BoardService {
	public BoardResultDto boardDetail(BoardParamDto boardParamDto);

	public BoardResultDto boardList(BoardParamDto boardParamDto);
	//public int boardListTotalCount();	

	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request);

}
