package model.service;

import java.sql.SQLException;

import model.dao.UserDAO;
import model.dto.User;

public class UserService {
	private UserDAO userDao = new UserDAO();
	
	public boolean registerUser(User user) throws Exception{
		return userDao.insertUser(user) > 0;
	}
	public String login(String userId,String passWord) throws Exception{
		return userDao.login(userId, passWord);
		
	}
	public boolean modifyUser(User user) throws Exception {
		return userDao.updateUser(user) > 0;
	}
	public boolean deleteUser(String userId) throws SQLException {
		
		return userDao.deleteUser(userId) > 0;
	}
}
