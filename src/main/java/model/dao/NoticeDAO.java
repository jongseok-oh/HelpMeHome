package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dto.Notice;
import util.DBUtil;

public class NoticeDAO {

	public List<Notice> getNotices() throws SQLException {
		List<Notice> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select no,title,writer,date,info from notice";
		
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			rs = pstmt.executeQuery();
			
			// step5
			
			while (rs.next()) {
				list.add(new Notice(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			}
			
		}finally {
			DBUtil.close(rs,pstmt,conn);
		}
		return list;
		
	}

	public Notice getNotice(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;
		String sql ="select no,title,writer,date,info from notice where no = ?";
		
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			// step5
			if (rs.next()) {
				notice = new Notice(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}
			
		} finally {
			DBUtil.close(rs,pstmt,conn);
		}
		return notice;
	}

	public int modifyNotice(Notice notice) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update notice set title = ?,info = ? where no = ?";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getInfo());
			pstmt.setInt(3, notice.getNo());
			
			
			int rowCnt = pstmt.executeUpdate();
			System.out.println(rowCnt+" 행이 처리되었습니다.");
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
	}

	public int removeNotice(int no) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from notice where no = ?";
		try {
			// step2
			conn = DBUtil.getConnection();
			
			// step3
			pstmt = conn.prepareStatement(sql);
			
			// step4
			pstmt.setInt(1, no);
			int rowCnt = pstmt.executeUpdate();
			
			return rowCnt;
		}finally {
			DBUtil.close(pstmt,conn);
		}
	}

}
