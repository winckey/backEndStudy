package com.my.house.service;

import com.my.house.dto.DealParamDto;
import com.my.house.dto.DealResultDto;



public interface DealService {
	public DealResultDto dealList(DealParamDto dealParamDto);
	public DealResultDto dealDetail(DealParamDto dealParamDto);
	public int ListTotalCnt(String searchWord); // 寃��깋�뼱 �엳�쓣 �븣
}
