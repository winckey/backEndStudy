package com.my.house.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.house.dto.AgentDto;
import com.my.house.dto.DealDto;
import com.my.house.dto.HouseDto;
import com.my.house.dto.HouseParamDto;



@Mapper
public interface AgentDao {
	
	public List<AgentDto> agentList(int userNo);

	public AgentDto agentDetail(int agentNo);

	public List<HouseDto> agentHouseList(int agentNo);

}

