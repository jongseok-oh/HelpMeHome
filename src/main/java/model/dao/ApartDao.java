package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.dto.BaseAddress;
import model.dto.HouseDeal;
import model.dto.Houseinfo;
import model.dto.SidoCode;
import util.DBUtil;

public class ApartDao {
	
	public List<Houseinfo> getHouseinfoByDongCode(String dongCode){
		List<Houseinfo> houseinfos = new ArrayList<>();
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from houseinfo where dongCode = ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setString(1, dongCode);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 int aptCode = rs.getInt(1);
				String aptName = rs.getString(2);
				
				String dongName= rs.getString(4);
				int buildYear = rs.getInt(5);
				String jibun= rs.getString(6);
				String lat = rs.getString(7);
				String lng = rs.getString(8);
				String img = rs.getString(9);
				
				Houseinfo houseinfo = new Houseinfo(
						aptCode, aptName, dongCode, dongName
						,buildYear, jibun, lat, lng, img);
				houseinfos.add(houseinfo);
			 }
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return houseinfos;
	}
	
	public List<HouseDeal> getHouseDealByAptCode(int aptCode){
		List<HouseDeal> deals = new ArrayList<>();
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 String sql = "select * from housedeal where aptCode = ?";
		 try {
			 conn = DBUtil.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 pstmt.setInt(1, aptCode);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				int no = rs.getInt(1);
				// aptCode 2
				String dealAmount = rs.getString(3);
				String dealYear = rs.getString(4);
				String dealMonth = rs.getString(5);
				String dealDay = rs.getString(6);
				String area = rs.getString(7);
				String floor = rs.getString(8);
				String type = rs.getString(9);
				String rentMoney = rs.getString(10);
				
				HouseDeal houseDeal = new HouseDeal(
						no, aptCode, dealAmount, dealYear, dealMonth,
						dealDay, area, floor, type, rentMoney);
				
				deals.add(houseDeal);
			 }
			 System.out.println(deals.size());
			 return deals;
		 }catch (Exception e) {
			 e.printStackTrace();
		 }finally {
			 DBUtil.close(conn,pstmt,rs);
		 }
		 return deals;
	}
}
