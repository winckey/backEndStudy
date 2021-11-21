package com.my.house.service;

import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;





public interface DealService {
	public HouseResultDto dealList(HouseParamDto dealParamDto);
	public HouseResultDto dealDetail(HouseParamDto dealParamDto);
	public int ListTotalCnt(String searchWord); // 寃��깋�뼱 �엳�쓣 �븣
}
