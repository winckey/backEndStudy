package com.my.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.DealDto;
import com.my.house.dto.HouseParamDto;



@Mapper
public interface DealDao {
	public List<DealDto> dealList(HouseParamDto dealParamDto);
	public DealDto dealDetail(HouseParamDto dealParamDto);
	//public int ListTotalCnt(String searchWord);
	//public HouseDto houseDetail(String aptName);

}
