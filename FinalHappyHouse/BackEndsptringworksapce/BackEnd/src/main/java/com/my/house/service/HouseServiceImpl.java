package com.my.house.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.HouseDao;
import com.my.house.dto.DealDto;
import com.my.house.dto.HouseDto;
import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;

@Service
public class HouseServiceImpl implements HouseService {

	// DealDaoImpl 媛앹껜 媛��졇�삤湲�
	@Autowired
	HouseDao dao;

	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	// singleton
	private static HouseServiceImpl instance;

	private HouseServiceImpl() {
	}

	@Override
	public HouseResultDto houseList(HouseParamDto houseParamDto) {

		HouseResultDto houseResultDto = new HouseResultDto();

		try {
			List<HouseDto> list = dao.houseList(houseParamDto);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}

		return houseResultDto;
	}
	
	@Override
	public HouseResultDto houseListSearchWord(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			List<HouseDto> list = dao.houseListSearchWord(houseParamDto);
			houseResultDto.setList(list);
			houseResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}
	

	@Override
	public HouseResultDto dealDetail(HouseParamDto houseParamDto) {
		HouseResultDto houseResultDto = new HouseResultDto();
		
		try {
			
			
			List<DealDto> dealDtos = dao.dealList(houseParamDto);//
			System.out.println("dealDtos ok : "+ dealDtos );
			HouseDto houseDto = dao.houseDetail(houseParamDto);//
			System.out.println("houseDto ok : "+ houseDto );
			int agentNo = dao.agentNo(houseParamDto);//
			System.out.println("agentNo ok : "+ agentNo );
			String agentName = dao.agentName(agentNo);//
			System.out.println("agentName ok : "+ agentName );
			
			
			
			houseResultDto.setAgentNo(agentNo);
			houseResultDto.setAgentName(agentName);
			houseResultDto.setDealList(dealDtos);
			houseResultDto.setDto(houseDto);
			
			houseResultDto.setResult(SUCCESS);
			System.out.println("house setResult : "+houseResultDto.getResult());
			
		}catch(Exception e) {
			e.printStackTrace();
			houseResultDto.setResult(FAIL);
		}
		
		return houseResultDto;
	}

	@Override
	public int ListTotalCnt(String searchWord) {
		// TODO Auto-generated method stub
		return 0;
	}



}
