package model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.dao.LocationDao;
import model.dto.DongCode;
import model.dto.GugunCode;
import model.dto.SidoCode;

public class LocationService {
	private LocationDao locationDao = new LocationDao();
	
	// static instance
	private Map<String, List<String>> sidoGunMap = new HashMap<>();
	private Map<String, List<String>> gugunDongMap = new HashMap<>();
	private List<SidoCode> sidoCodes;
	private List<GugunCode> gugunCodes;
	private List<DongCode> dongCodes;
	// -----------------------------------------------------------------
	
	public static LocationService getInstance() {
		return LazyHolder.instance;
	}
	
	private static class LazyHolder{
		private static final LocationService instance = new LocationService();
	}
	
	private LocationService() {
		sidoCodes = locationDao.getSidoCodeList();
		gugunCodes = locationDao.getGugunCodeList();
		dongCodes = locationDao.getDongCodeList();
		
		// select box를 위한 자료구조 생성
		for(SidoCode s: sidoCodes) {
			String sStart = s.getSidoCode().substring(0, 2);
			String sName = s.getSidoName();
			sidoGunMap.put(sName, new ArrayList<String> ());
			
			for(GugunCode g: gugunCodes) {
				String gStart = g.getGugunCode().substring(0, 5);
				String gName = g.getGugunName();
				
				if(gStart.startsWith(sStart))
					sidoGunMap.get(sName).add(gName);
				
				gugunDongMap.put(gName, new ArrayList<String>());
				
				for(DongCode d: dongCodes) {
					String dString = d.getDongCode();
					
					if(dString.startsWith(gStart))
						gugunDongMap.get(gName).add(d.getDongName());
				}
			}
			
		}
	}
	
	public List<SidoCode> getSidoList(){
		return this.sidoCodes;
	}
	
	public List<String> getGugunListBySido(String sidoName){
		if(sidoGunMap.containsKey(sidoName))
			return sidoGunMap.get(sidoName);
		return null;
	}
	
	public List<String> getDongListByGugun(String gugunName){
		if(gugunDongMap.containsKey(gugunName))
			return gugunDongMap.get(gugunName);
		return null;
	}
	
	public String getDongCode(String sidoName, String gugunName, String dongName) {
		
		for(DongCode dc: dongCodes) {
			if(dc.getSidoName().equals(sidoName)
			&& dc.getGugunName().equals(gugunName)
			&& dc.getDongName().equals(dongName))
			return dc.getDongCode();
		}
		return null;
	}
}
