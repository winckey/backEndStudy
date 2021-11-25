package com.my.house.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.FavoriteDao;
import com.my.house.dto.FavoriteResultDto;



@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	FavoriteDao favoriteDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	
	
	@Override
	public FavoriteResultDto addFavorite(int userNo, int agentNo) {
		
		FavoriteResultDto favoriteResultDto = new FavoriteResultDto();
		HashMap<String,Integer> map = new HashMap<>();
		map.put("userNo",userNo);
		map.put("agentNo", agentNo);
		
		if( favoriteDao.addFavorite(map) == 1 ) {
			favoriteResultDto.setResult(SUCCESS);
		}else {
			favoriteResultDto.setResult(FAIL);
		}
		
		return favoriteResultDto;
	
	}

	@Override
	public FavoriteResultDto removeFavorite(int userNo, int agentNo) {
		FavoriteResultDto favoriteResultDto = new FavoriteResultDto();
		HashMap<String,Integer> map = new HashMap<>();
		map.put("userNo",userNo);
		map.put("agentNo", agentNo);
		if( favoriteDao.removeFavorite(map) == 1 ) {
			favoriteResultDto.setResult(SUCCESS);
		}else {
			favoriteResultDto.setResult(FAIL);

		}
		return favoriteResultDto;
	}

}
