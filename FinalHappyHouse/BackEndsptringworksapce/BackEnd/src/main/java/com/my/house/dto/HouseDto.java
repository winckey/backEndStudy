package com.my.house.dto;

public class HouseDto {

	private int houseNo;
	private String area;
	private String code;
	private String dong;
	private String houseName;
	private String buildYear;
	private String jibun;
	private String lat;
	private String lng; 
	private String img;
	
	

	public HouseDto() {
		super();
	}
	public HouseDto(int houseNo, String area, String code, String dong, String aptName, String buildYear, String jibun,
			String lat, String lng) {
		super();
		this.houseNo = houseNo;
		this.area = area;
		this.code = code;
		this.dong = dong;
		this.houseName = aptName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
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
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	


	
}
