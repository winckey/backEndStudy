package com.my.house.service;

import com.my.house.dto.AgentResultDto;
import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;



public interface AgentService {

//	AgentResultDto agentList();
	
	// 즐겨찾기 기능
	AgentResultDto agentList(int userNo);
	AgentResultDto agentDetail(int agentNo);
	
	
	
	
}
