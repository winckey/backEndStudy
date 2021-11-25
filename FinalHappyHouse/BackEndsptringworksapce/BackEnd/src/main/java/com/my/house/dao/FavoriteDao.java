package com.my.house.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FavoriteDao {
	public int addFavorite(HashMap map);
	public int removeFavorite(HashMap map);
	
		
}