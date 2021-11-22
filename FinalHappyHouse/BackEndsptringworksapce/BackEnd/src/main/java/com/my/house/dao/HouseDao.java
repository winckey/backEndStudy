package com.my.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.AgentDto;
import com.my.house.dto.DealDto;
import com.my.house.dto.HouseDto;
import com.my.house.dto.HouseParamDto;



@Mapper
public interface HouseDao {
	public List<HouseDto> houseList(HouseParamDto houseParamDto);
	public HouseDto houseDetail(HouseParamDto houseParamDto);
	//public int ListTotalCnt(String searchWord);
	//public HouseDto houseDetail(String aptName);
	public List<HouseDto> houseListSearchWord(HouseParamDto houseParamDto);
	public String agentName(int agentNo);
	public List<DealDto> dealList(HouseParamDto houseParamDto);
	public int agentNo(HouseParamDto houseParamDto);
	public AgentDto agent(HouseParamDto houseParamDto);

}

