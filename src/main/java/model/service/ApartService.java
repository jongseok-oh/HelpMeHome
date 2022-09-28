package model.service;

import java.util.List;

import model.dao.ApartDao;
import model.dto.BaseAddress;
import model.dto.HouseDeal;
import model.dto.Houseinfo;

public class ApartService {
	private ApartDao apartDao = new ApartDao();
	
	public List<Houseinfo> getHouseinfoListByDongCode(String dongCode){
		return apartDao.getHouseinfoByDongCode(dongCode);
	}
	
	public List<HouseDeal> getHouseDealListByAptCode(int aptCode){
		return apartDao.getHouseDealByAptCode(aptCode);
	}
}
