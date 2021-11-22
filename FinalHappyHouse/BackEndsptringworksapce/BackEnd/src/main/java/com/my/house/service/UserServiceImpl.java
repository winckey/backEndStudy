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

import com.my.house.dao.UserDao;
import com.my.house.dto.BoardFileDto;
import com.my.house.dto.UserDto;
import com.my.house.dto.UserResultDto;



@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	String uploadFolder = "upload";
	
	/* for production code */
	//uploadPath = getServletContext().getRealPath("/");
	// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\
	
	/* for eclipse development code */
	String uploadPath = "C:" + File.separator + "SSAFY" + File.separator + "ssafy6_sts3_boot_live" 
			+ File.separator + "fianlSpringBootVue" 
			+ File.separator + "src" 
			+ File.separator + "main"
			+ File.separator + "resources"
			+ File.separator + "static";

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	@Override
	public UserResultDto userRegister(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		if( userDao.userRegister(userDto) == 1 ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(String userId) {
		UserResultDto userResultDto = new UserResultDto();

		if( userDao.userDelete(userId) == 1 ) {
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);

		}
		return userResultDto;
	}

	// img없이 업데이트 하는 코드
	@Override
	public UserResultDto userUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		
		if( userDao.userUpdate(userDto) == 1 ) {
			
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);

		}
	
		
		return userResultDto;
	}
//
//	@Override
//	@Transactional
//	public UserResultDto userUpdate(UserDto userDto, MultipartHttpServletRequest request) {
//		UserResultDto userResultDto = new UserResultDto();
//		
//		try {
//			userDao.userUpdate(userDto);
//
//			List<MultipartFile> fileList = request.getFiles("file");
//			
//			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
//			if (!uploadDir.exists()) uploadDir.mkdir();
//			
//	    	List<String> fileUrlList = userDao.userFileUrlDeleteList(userDto.getUserId());	
//			for(String fileUrl : fileUrlList) {	
//				File file = new File(uploadPath + File.separator, fileUrl);
//				if(file.exists()) {
//					file.delete();
//				}
//			}
//
//		userDao.userFileDelete(userDto.getUserId()); // �뜝�룞�삕�뜝�떛釉앹삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕
//	    	
//	    	
//			for (MultipartFile part : fileList) {
//				String boardId = userDto.getUserId();
//				
//				String fileName = part.getOriginalFilename();
//				
//				//Random File Id
//				UUID uuid = UUID.randomUUID();
//				
//				//file extension
//				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
//			
//				String savingFileName = uuid + "." + extension;
//			
//				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				
//				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
//				part.transferTo(destFile);
//		    
//			    // Table Insert
//			    BoardFileDto boardFileDto = new BoardFileDto();
//			    boardFileDto.setBoardId(boardId);
//			    boardFileDto.setFileName(fileName);
//			    boardFileDto.setFileSize(part.getSize());
//				boardFileDto.setFileContentType(part.getContentType());
//				String boardFileUrl = uploadFolder + "/" + savingFileName;
//				boardFileDto.setFileUrl(boardFileUrl);
//				
//				userDao.boardFileInsert(boardFileDto);
//			}
//
//			userResultDto.setResult(SUCCESS);
//			
//		}catch(IOException e) {
//			e.printStackTrace();
//			userResultDto.setResult(FAIL);
//		}
//		
//		return userResultDto;
//	}

	@Override
	public UserResultDto userPasswordUpdate(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();
		System.out.println(userDto);
		if( userDao.userPasswordUpdate(userDto) == 1 ) {
			
			userResultDto.setResult(SUCCESS);
		}else {
			userResultDto.setResult(FAIL);

		}
	
		
		return userResultDto;
	}
}
