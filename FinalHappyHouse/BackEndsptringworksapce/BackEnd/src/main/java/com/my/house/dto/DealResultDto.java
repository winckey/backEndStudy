package com.my.house.dto;

import java.util.List;

public class DealResultDto {
	private int result;
	private DealDto dto;
	private List<DealDto> list;
	private int count;
	
	private boolean isOwner;
	
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public DealDto getDto() {
		return dto;
	}
	public void setDto(DealDto dto) {
		this.dto = dto;
	}

	public List<DealDto> getList() {
		return list;
	}
	public void setList(List<DealDto> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
