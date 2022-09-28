package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.BaseAddress;
import model.dto.UserArea;
import util.DBUtil;

public class AreaDAO {

	public int registerUserArea(UserArea userArea) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into userarea(userId,dongCode) values(?,?)";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setString(1, userArea.getUserId());
			pstmt.setString(2, userArea.getDongCode());
			
			
			int rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
	}

	public List<UserArea> getUserAreaListByUserId(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<UserArea> list = new ArrayList<UserArea>();
		String sql = "select userId,dongCode from userarea";
		
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			rs = pstmt.executeQuery();
			
			// step5
			
			while (rs.next()) {
				list.add(new UserArea(rs.getString(1),rs.getString(2)));
			}
			return list;
		}finally {
			DBUtil.close(rs,pstmt,conn);
		}
		
	}

	public BaseAddress getSiGuDong(String dongCode) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BaseAddress baseAdress = null;
		String sql = "select dongCode,sidoName,gugunName,dongName from baseaddress where dongCode=?";
		
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dongCode);
			// step4
			rs = pstmt.executeQuery();
			
			// step5
			
			while (rs.next()) {
				baseAdress = new BaseAddress(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			return baseAdress;
		}finally {
			DBUtil.close(rs,pstmt,conn);
		}
		
		
	}

	public int remove(String userId, String dongCode) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from userarea where userId = ? and dongCode=?";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setString(1, userId);
			pstmt.setString(2, dongCode);
			
			int rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
	}
	
}
