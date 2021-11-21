package com.my.house.dto;

public class HouseDto {

	private int house_no;
	private String area;
	private String code;
	private String dong;
	private String houseName;
	private String buildYear;
	private String jibun;
	private String img;
	private String lat;
	private String lng;

	public HouseDto() {
		super();
	}

	public HouseDto(int house_no, String area, String code, String dong, String houseName, String buildYear,
			String jibun, String img, String lat, String lng) {
		super();
		this.house_no = house_no;
		this.area = area;
		this.code = code;
		this.dong = dong;
		this.houseName = houseName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.img = img;
		this.lat = lat;
		this.lng = lng;
	}

	public int getHouse_no() {
		return house_no;
	}

	public void setHouse_no(int house_no) {
		this.house_no = house_no;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	@Override
	public String toString() {
		return "HouseDto [house_no=" + house_no + ", area=" + area + ", code=" + code + ", dong=" + dong
				+ ", houseName=" + houseName + ", buildYear=" + buildYear + ", jibun=" + jibun + ", img=" + img
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}

}
