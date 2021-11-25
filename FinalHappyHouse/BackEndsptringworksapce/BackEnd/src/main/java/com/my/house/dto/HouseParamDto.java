package com.my.house.dto;

public class HouseParamDto {
	
	private int limit;
	private int offset;
	private String searchWord;
	private int houseNo;
	private int min;
	
	private int max;
	
	public HouseParamDto() {
		super();
	}

	public HouseParamDto(int limit, int offset, String searchWord, int houseNo) {
		super();
		this.limit = limit;
		this.offset = offset;
		this.searchWord = searchWord;
		this.houseNo = houseNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	
	
}
