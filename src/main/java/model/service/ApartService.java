package model.service;

import java.util.List;

import model.dao.ApartDao;
import model.dto.BaseAddress;

public class ApartService {
	private ApartDao apartDao = new ApartDao();
	
	// 한 번에 출력할 개수 
	private final int apartCnt = 6;
	
	public List<BaseAddress> getBaseAddressListByPageNum(String dongCode, int pageNum){
		
		int limitStart = (pageNum - 1) * apartCnt;
		return apartDao.getBaseAddressListByDongCodeLimit(dongCode, limitStart, apartCnt);
	}
	
	public int getApartPageCnt(String dongCode) {
		return (apartDao.getApartCnt(dongCode) + apartCnt - 1)/apartCnt;
	}
}
