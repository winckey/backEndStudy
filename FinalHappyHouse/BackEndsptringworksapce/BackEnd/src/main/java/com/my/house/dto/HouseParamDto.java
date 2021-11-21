package com.my.house.dto;

public class HouseParamDto {
	
	private int limit;
	private int offset;
	private String searchWord;
	private int houseNo;
	
	
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

	
	
}
