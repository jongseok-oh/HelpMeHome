package model.service;

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
}
