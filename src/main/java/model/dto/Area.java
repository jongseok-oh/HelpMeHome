package model.dto;

public class Area {
	String sanggaCode;
	String sanghoName;
	String eobjongName;
	String dongCode;
	String roadAddress;
	String postNum;
	String lng;
	String lat;
	
	public Area(String sanggaCode, String sanghoName, String eobjongName, String dongCode, String roadAddress,
			String postNum, String lng, String lat) {
		super();
		this.sanggaCode = sanggaCode;
		this.sanghoName = sanghoName;
		this.eobjongName = eobjongName;
		this.dongCode = dongCode;
		this.roadAddress = roadAddress;
		this.postNum = postNum;
		this.lng = lng;
		this.lat = lat;
	}
	public String getSanggaCode() {
		return sanggaCode;
	}
	public void setSanggaCode(String sanggaCode) {
		this.sanggaCode = sanggaCode;
	}
	public String getSanghoName() {
		return sanghoName;
	}
	public void setSanghoName(String sanghoName) {
		this.sanghoName = sanghoName;
	}
	public String getEobjongName() {
		return eobjongName;
	}
	public void setEobjongName(String eobjongName) {
		this.eobjongName = eobjongName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getPostNum() {
		return postNum;
	}
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	
	
}
