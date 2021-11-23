package com.my.house.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dao.BoardDao;
import com.my.house.dto.BoardDto;
import com.my.house.dto.BoardFileDto;
import com.my.house.dto.BoardParamDto;
import com.my.house.dto.BoardResultDto;

	@Service
	public class BoardServiceImpl implements BoardService {
	
		@Autowired
		BoardDao dao;
		String uploadFolder = "upload";
	
		/* for production code */
		//uploadPath = getServletContext().getRealPath("/");
		// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\

		/* for eclipse development code */
		String uploadPath = "/Users" + File.separator + "sac" + File.separator + "ssafy" 
				+ File.separator + "code" 
				+ File.separator + "Final_project"
				+ File.separator + "ChoiIsaac"
				+ File.separator + "backend"
				+ File.separator + "Backend" 
				+ File.separator + "src" 
				+ File.separator + "main"
				+ File.separator + "resources"
				+ File.separator + "static";
		/* 업로드 후 upload 폴더 refresh 하거나 preferences / workspace - refresh... 2개 option check */
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
//@transaction :rollback 하기 위한 함수 - c u d쿼리 호출 할때오류 발생시 되돌리기 위해서!! read문빼고 무조건 달기 
	@Override
	@Transactional
	public BoardResultDto boardInsert(BoardDto dto, MultipartHttpServletRequest request) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			dao.boardInsert(dto); // useGeneratedKeys="true" keyProperty="boardId"

			// for transaction test
//			String s = null;
//			s.length();
			
			List<MultipartFile> fileList = request.getFiles("file");
	
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();

			for (MultipartFile part : fileList) {

				int boardId = dto.getBoardId();
				
				String fileName = part.getOriginalFilename();
				
				//Random File Id
				UUID uuid = UUID.randomUUID();
				
				//file extension , 확장자만 떼어내고
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
				// uuid [. 확장자 ]파일이름 만들기 
				String savingFileName = uuid + "." + extension;
				// 파일 객체만 생성
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				// output stream 기능을 구현 
				part.transferTo(destFile);
		    
			    // Table Insert
			    BoardFileDto boardFileDto = new BoardFileDto();
			    boardFileDto.setBoardId(boardId);
			    boardFileDto.setFileName(fileName);
			    boardFileDto.setFileSize(part.getSize());
				boardFileDto.setFileContentType(part.getContentType());
				String boardFileUrl = uploadFolder + "/" + savingFileName;
				boardFileDto.setFileUrl(boardFileUrl);
				
				dao.boardFileInsert(boardFileDto);
			}

			boardResultDto.setResult(SUCCESS);
			
		}catch(IOException e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		return boardResultDto;
	}


	@Override
	public BoardResultDto boardList(BoardParamDto boardParamDto) {
		
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
			List<BoardDto> list = dao.boardList(boardParamDto);
			int count = dao.boardListTotalCount();			
			boardResultDto.setList(list);
			boardResultDto.setCount(count);
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}

	@Override
	@Transactional
	public BoardResultDto boardDetail(BoardParamDto boardParamDto) {
		System.out.println(" 서비스 시작");
		BoardResultDto boardResultDto = new BoardResultDto();
		
		try {
//			int userReadCnt = dao.boardUserReadCount(boardParamDto);
//			// 조회수 관련 
//			if( userReadCnt == 0 ) {
//				dao.boardUserReadInsert(boardParamDto.getBoardId(), boardParamDto.getUserNo());
//				dao.boardReadCountUpdate(boardParamDto.getBoardId());
//			}
			
			System.out.println(" 서비스에서 dao 넘어가기전");
			BoardDto dto = dao.boardDetail(boardParamDto);
			System.out.println("boardDto "+ dto);
//			List<BoardFileDto> fileList = dao.boardDetailFileList(dto.getBoardId());
//
//			dto.setFileList(fileList);
			
			boardResultDto.setDto(dto);
			
			
			
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}

}