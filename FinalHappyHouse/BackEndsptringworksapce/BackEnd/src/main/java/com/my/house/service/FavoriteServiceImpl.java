package com.my.house.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.my.house.dao.UserDao;
import com.my.house.dto.UserDto;
import com.my.house.dto.UserFileDto;
import com.my.house.dto.UserResultDto;



@Service
public class FavoriteServiceImpl implements UserService{
	
	@Autowired
	UserDao userDao;
	String uploadFolder = "upload";
//	
//	/* for production code */
//	//uploadPath = getServletContext().getRealPath("/");
//	// F:\SSAFY\ssafy5\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\BoardWebFileUpload\
//	
	/* for eclipse development code */
	String uploadPath = "/Users" + File.separator + "sac" + File.separator + "ssafy" 
			+ File.separator + "code" 
			+ File.separator + "Final_project"
			+ File.separator + "FinalHappyHouse" 
			+ File.separator + "BackEndsptringworksapce" 
			+ File.separator + "BackEnd" 
			+ File.separator + "src" 
			+ File.separator + "main"
			+ File.separator + "resources"
			+ File.separator + "static";
	
	
//	@Value("${app.fileupload.uploadDir}")
//    String uploadFolder;
//    
//    @Value("${app.fileupload.uploadPath}")
//    String uploadPath;
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

	@Override
	@Transactional
	public UserResultDto userUpdate(UserDto userDto, MultipartHttpServletRequest request) {
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			System.out.println("기존 정보만 업데이트 전 userDto: "+ userDto);
			userDao.userUpdate(userDto);
			System.out.println("기존 정보만 업데이트 후 userDto: "+ userDto);

			List<MultipartFile> fileList = request.getFiles("file");
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if (!uploadDir.exists()) uploadDir.mkdir();
			
			
	    	List<String> fileUrlList = userDao.userFileUrlDeleteList(userDto.getUserId());	
			for(String fileUrl : fileUrlList) {	
				File file = new File(uploadPath + File.separator, fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}

			userDao.userFileDelete(userDto.getUserId()); // 
		
	    		
			for (MultipartFile part : fileList) {
				String userId = userDto.getUserId();
				
				String fileName = part.getOriginalFilename();
				
				//Random File Id
				UUID uuid = UUID.randomUUID();
				
				//file extension
				String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
			
				String savingFileName = uuid + "." + extension;
			
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				System.out.println(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				part.transferTo(destFile);
		    
			    // Table Insert
			    UserFileDto userFileDto = new UserFileDto();
			    userFileDto.setUserId(userId);
			    userFileDto.setFileName(fileName);
			    userFileDto.setFileSize(part.getSize());
			    userFileDto.setFileContentType(part.getContentType());
				String userFileUrl = uploadFolder + "/" + savingFileName;
				userFileDto.setFileUrl(userFileUrl);
				
				userDao.userFileInsert(userFileDto);
				userDao.userFileUrlInsert(userFileDto);
			}

			userResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		
		return userResultDto;
	}

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

	@Override
	public UserResultDto userFavoriteAgentList(int userNo) {
		UserResultDto userResultDto = new UserResultDto();
		
		try {
			List<Integer> userFavoriteAgentList =  userDao.userFavoriteAgentList(userNo);  
			userResultDto.setUserFavoriteAgentList(userFavoriteAgentList);			
			userResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);

		}
		return userResultDto;
	}
}
