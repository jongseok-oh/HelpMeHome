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
	 
	 public String getSidoCodeBySidoName(String sidoName) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from sidocode where sidoName = ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sidoName);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) return rs.getString(1);
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return null;
	 }
	 
	 public List<String> getGugunNameListBySidoCode(String sidoCode){
		 List<String> gugunNames = new ArrayList<>();
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 
		 String sidoSub = sidoCode.substring(0,2);
		 String sql = "select gugunName from guguncode where gugunCode like concat(?, '%')";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sidoSub);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 gugunNames.add(rs.getString(1));
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return gugunNames;
	 }
	 
	 public List<String> getDongNameList(String sidoName, String gugunName){
		 List<String> dongNames = new ArrayList<>();
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select dongName from dongcode where sidoName = ? and gugunName = ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sidoName);
			 pstmt.setString(2, gugunName);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) dongNames.add(rs.getString(1));
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return dongNames;
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
	
	public String getDongCode(String sidoName, String gugunName, String dongName) {
		 
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from dongcode where sidoName = ? and gugunName = ? and dongName = ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, sidoName);
			 pstmt.setString(2, gugunName);
			 pstmt.setString(3, dongName);
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) return rs.getString(1);
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return null;
	 }
	
	
}
