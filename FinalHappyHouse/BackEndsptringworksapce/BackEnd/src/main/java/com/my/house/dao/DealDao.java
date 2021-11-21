package com.my.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.DealDto;
import com.my.house.dto.DealParamDto;



@Mapper
public interface DealDao {
	public List<DealDto> dealList(DealParamDto dealParamDto);
	public DealDto dealDetail(DealParamDto dealParamDto);
	//public int ListTotalCnt(String searchWord);
	//public HouseDto houseDetail(String aptName);

}
