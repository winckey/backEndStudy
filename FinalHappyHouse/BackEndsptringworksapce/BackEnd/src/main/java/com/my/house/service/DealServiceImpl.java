package com.my.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.house.dao.DealDao;
import com.my.house.dto.DealDto;
import com.my.house.dto.DealParamDto;
import com.my.house.dto.DealResultDto;




@Service
public class DealServiceImpl implements DealService {
	
	// DealDaoImpl 媛앹껜 媛��졇�삤湲�
	@Autowired
	DealDao dealDao;
	
	private static final int SUCCESS = 1;
	private static final int FAIL = -1;
	// singleton
	private static DealServiceImpl instance;
	private DealServiceImpl() {}
	

	@Override
	public DealResultDto dealList(DealParamDto dealParamDto) {
		
		
		DealResultDto boardResultDto = new DealResultDto();
		
		try {
			List<DealDto> list = dealDao.dealList(dealParamDto);
			//int count = dealDao.ListTotalCnt(dealParamDto.getSearchWord());	
			
			for (int i = 0; i < list.size(); i++) {
//				System.out.println(list.get(i).getNo());
			}
			
			boardResultDto.setList(list);
			boardResultDto.setCount(10);/// �씪�떒 �븯�뱶肄붾뵫
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}
	
	@Override
	public int ListTotalCnt(String searchWord) {
		//return dealDao.ListTotalCnt(searchWord);
		return 0;
	}
	
	@Override
	public DealResultDto dealDetail(DealParamDto dealParamDto) {
		DealResultDto boardResultDto = new DealResultDto();
		
		try {
			
			DealDto dto = dealDao.dealDetail(dealParamDto);
			System.out.println("dealDetail service " + dto);
			boardResultDto.setDto(dto);
			boardResultDto.setResult(SUCCESS);
			
		}catch(Exception e) {
			e.printStackTrace();
			boardResultDto.setResult(FAIL);
		}
		
		return boardResultDto;
	}
	

}