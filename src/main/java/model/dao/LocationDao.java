package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dto.BaseAddress;
import model.dto.DongCode;
import model.dto.GugunCode;
import model.dto.SidoCode;
import util.DBUtil;

public class LocationDao {
	
	 public List<SidoCode> getSidoCodeList(){
		 List<SidoCode> sidoCodes = new ArrayList<>();
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from sidocode";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 sidoCodes.add(new SidoCode(rs.getString(1), rs.getString(2)));
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return sidoCodes;
	 }
	 
	 public List<GugunCode> getGugunCodeList(){
		 List<GugunCode> gugunCodes = new ArrayList<>();
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from guguncode";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 gugunCodes.add(new GugunCode(rs.getString(1), rs.getString(2)));
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return gugunCodes;
	 }
	 
	 public List<DongCode> getDongCodeList(){
		 List<DongCode> dongCodes = new ArrayList<>();
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from dongcode";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 String dongCode = rs.getString(1);
				 String sidoName = rs.getString(2);
				 String gugunName = rs.getString(3);
				 String dongName = rs.getString(4);
				 dongCodes.add(new DongCode(dongCode, sidoName, gugunName, dongName));
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return dongCodes;
	 }
	 
		public BaseAddress getBaseAddressByDongCode(String dongCode){
			
			Connection conn = null;
			 PreparedStatement pstmt = null;
			 ResultSet rs = null;
			 String sql = "select * from baseaddress where dongCode = ?";
			 try {
				 conn = DBUtil.getConnection();
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, dongCode);
				 rs = pstmt.executeQuery();
				 
				 while(rs.next()) {
					 int no = rs.getInt(1);
					 String sidoName = rs.getString(2);
					String gugunName = rs.getString(3);
					String dongName = rs.getString(4);
					//String dongCode = rs.getString(5);
					String lat = rs.getString(6);
					String lng = rs.getString(7);
					return new BaseAddress(no, sidoName, gugunName, dongName, dongCode, lat, lng);
				 }
			 }catch (Exception e) {
				 e.printStackTrace();
			 }finally {
				 DBUtil.close(conn,pstmt,rs);
			 }
			 return null;
		}
}
