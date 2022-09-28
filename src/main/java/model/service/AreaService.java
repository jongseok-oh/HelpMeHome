package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.AreaDAO;
import model.dao.LocationDao;
import model.dto.BaseAddress;
import model.dto.UserArea;

public class AreaService {
	private AreaDAO areaDao = new AreaDAO();

	public boolean registerUserArea(UserArea userArea) throws SQLException {
		return areaDao.registerUserArea(userArea) > 0;
	}

	public List<UserArea> getUserAreaListByUserId(String userId) throws SQLException {
		return areaDao.getUserAreaListByUserId(userId);
	}

	public BaseAddress getSiGuDong(String dongCode) throws SQLException {
		return areaDao.getSiGuDong(dongCode);
	}

	public boolean deleteUserArea(String userId, String dongCode) throws SQLException {
		return areaDao.remove(userId,dongCode) > 0;
	}
}
