package com.my.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.AgentDao;
import com.my.house.dao.HouseDao;
import com.my.house.dto.AgentDto;
import com.my.house.dto.AgentResultDto;
import com.my.house.dto.BoardDto;
import com.my.house.dto.BoardFileDto;
import com.my.house.dto.BoardResultDto;
import com.my.house.dto.DealDto;
import com.my.house.dto.HouseDto;
import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;

@Service
public class AgentServiceImpl implements AgentService {

	// DealDaoImpl 揶쏆빘猿� 揶쏉옙占쎌죬占쎌궎疫뀐옙
	@Autowired
	AgentDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	// singleton
	private static AgentServiceImpl instance;

	private AgentServiceImpl() {
	}

	@Override
	public AgentResultDto agentList(int userNo) {
		AgentResultDto agentResultDto = new AgentResultDto();

		try {

			List<AgentDto> agentDtos = dao.agentList(userNo);
			for (int i = 0; i < agentDtos.size(); i++) {
				if(agentDtos.get(i).getAgentProfileImageUrl() ==null)
				{
					agentDtos.get(i).setAgentProfileImageUrl("assets/img/noImage.gif");
				}
			}
			//
			System.out.println("agentDtos ok : " + agentDtos);
			agentResultDto.setList(agentDtos);
			agentResultDto.setResult(SUCCESS);
			System.out.println("house setResult : " + agentResultDto.getResult());
		} catch (

		Exception e) {
			e.printStackTrace();
			agentResultDto.setResult(FAIL);
		}

		return agentResultDto;
	}

	@Override
	public AgentResultDto agentDetail(int agentNo) {
		AgentResultDto agentResultDto = new AgentResultDto();

		try {
			AgentDto dto = dao.agentDetail(agentNo);
			List<HouseDto> list = dao.agentHouseList(agentNo);
			
			
			agentResultDto.setAgentDto(dto);
			agentResultDto.setHouselist(list);
			agentResultDto.setResult(SUCCESS);
			
			
			System.out.println("agentResultDto : " + agentResultDto);
		} catch (Exception e) {
			e.printStackTrace();
			agentResultDto.setResult(FAIL);
		}

		return agentResultDto;
	}

}
