package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.LocationDao;
import model.dto.BaseAddress;
import model.dto.DongCode;
import model.dto.GugunCode;
import model.dto.SidoCode;

public class LocationService {
	private LocationDao locationDao = new LocationDao();
	
	public static LocationService getInstance() {
		return LazyHolder.instance;
	}
	
	private static class LazyHolder{
		private static final LocationService instance = new LocationService();
	}
	
	private LocationService() {
	}
	
	public List<SidoCode> getSidoList(){
		return locationDao.getSidoCodeList();
	}
	
	public List<String> getGugunListBySido(String sidoName){
		String sidoCode = locationDao.getSidoCodeBySidoName(sidoName);
		return locationDao.getGugunNameListBySidoCode(sidoCode);
	}
	
	public List<String> getDongNameList(String sidoName, String gugunName){
		return locationDao.getDongNameList(sidoName, gugunName);
	}
	
	public String getDongCode(String sidoName, String gugunName, String dongName) {
		return locationDao.getDongCode(sidoName, gugunName, dongName);
	}
	
	public BaseAddress getBaseAddressByDongCode(String dongCode) {
		return locationDao.getBaseAddressByDongCode(dongCode);
	}
}
