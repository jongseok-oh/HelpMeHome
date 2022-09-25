package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dto.BaseAddress;
import model.dto.SidoCode;
import util.DBUtil;

public class ApartDao {
	
	public List<BaseAddress> getBaseAddressListByDongCodeLimit(String dongCode, int limitStart, int limitCnt){
		List<BaseAddress> addresses = new ArrayList<>();
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from baseaddress where dongCode = ? limit ?, ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dongCode);
			 pstmt.setInt(2, limitStart);
			 pstmt.setInt(3, limitCnt);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 int no = rs.getInt(1);
				 String sidoName = rs.getString(2);
				String gugunName = rs.getString(3);
				String dongName = rs.getString(4);
				//String dongCode = rs.getString(5);
				String lat = rs.getString(6);
				String lng = rs.getString(7);
				addresses.add(new BaseAddress(no, sidoName, gugunName, dongName, dongCode, lat, lng));
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return addresses;
	}
	
	public List<BaseAddress> getBaseAddressListByDongCode(String dongCode){
		return getBaseAddressListByDongCodeLimit(dongCode, 0, 6);
	}
}
