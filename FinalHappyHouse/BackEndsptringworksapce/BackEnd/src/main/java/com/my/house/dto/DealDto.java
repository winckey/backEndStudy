package com.my.house.dto;

public class DealDto {

	private int deal_no;
	private int houseNo;
	private String dealAmount;
	private String dealYear;
	private String dealMonth;
	private String dealDay;
	private String area;
	private String code;
	private String dong;
	private String houseName;
	private String buildYear;
	private String jibun;
	// 留ㅻЪ 異붽� �젙蹂� -> �쐞, 寃쎈룄 
	private String lat;
	private String lng;
	
	
	
	
	private String floor;

	public DealDto() {
		super();
	}
	public DealDto(int no, String dealAmount, String dealYear, String dealMonth, String dealDay, String area,
			String code, String dong, String aptname, String lat, String lng) {
		super();
		this.deal_no = no;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.code = code;
		this.dong = dong;
		this.houseName = aptname;
		this.lat = lat;
		this.lng = lng;
	}
	public int getDeal_no() {
		return deal_no;

	public int getHouseNo() {
		return houseNo;
	}
	public void setDeal_no(int deal_no) {
		this.deal_no = deal_no;

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;

	public String getFloor() {
		return floor;
	}
	@Override
	public String toString() {
		return "DealDto [deal_no=" + deal_no + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth="
				+ dealMonth + ", dealDay=" + dealDay + ", area=" + area + ", code=" + code + ", dong=" + dong
				+ ", houseName=" + houseName + ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat
				+ ", lng=" + lng + "]";

	public void setFloor(String floor) {
		this.floor = floor;
	}
	

}
