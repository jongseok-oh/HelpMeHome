package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.User;
import util.DBUtil;

public class UserDAO {
	public int insertUser(User user) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into user(userId,passWord,name,phoneNumber) values(?,?,?,?)";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassWord());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getPhoneNumber());
			
			
			int rowCnt = pstmt.executeUpdate();
			System.out.println("user insert");
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
		
	}
	
	public String login(String userId,String passWord) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select name from user where userId = ? and passWord = ?";
		try {
			conn= DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, passWord);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}
		} finally {
			DBUtil.close(conn,pstmt,rs);
		}
		return null;
	}

	public int updateUser(User user) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update user set passWord=?  ,name = ?,phoneNumber = ? where userId = ?";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setString(1, user.getPassWord());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPhoneNumber());
			pstmt.setString(4, user.getUserId());
			
			int rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
	}

	public int deleteUser(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql="delete from user where userId = ?";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			int cnt = pstmt.executeUpdate();
			return cnt;
		} finally {
			DBUtil.close(conn,pstmt);
		}
	}
	
}
