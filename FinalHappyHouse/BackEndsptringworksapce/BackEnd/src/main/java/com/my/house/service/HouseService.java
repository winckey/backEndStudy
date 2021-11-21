package com.my.house.service;

import com.my.house.dto.HouseParamDto;
import com.my.house.dto.HouseResultDto;



public interface HouseService {
	public HouseResultDto houseList(HouseParamDto houseParamDto);
	public HouseResultDto dealDetail(HouseParamDto houseParamDto);
	public int ListTotalCnt(String searchWord); // �??��?�� ?��?�� ?��
	public HouseResultDto houseListSearchWord(HouseParamDto houseParamDto);
}
