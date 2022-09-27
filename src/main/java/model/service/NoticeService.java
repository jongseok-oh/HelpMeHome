package model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.NoticeDAO;
import model.dto.Notice;

public class NoticeService {
	private NoticeDAO noticeDao = new NoticeDAO();

	public List<Notice> getNotices() throws SQLException {
		return noticeDao.getNotices();
	}

	public Notice getNotice(int no) throws SQLException {
		return noticeDao.getNotice(no);
	}

	public boolean modifyNotice(Notice notice) throws SQLException {
		return noticeDao.modifyNotice(notice)>0;
	}

	public boolean deleteNotice(int no) throws SQLException {
		return noticeDao.removeNotice(no) > 0;
	}
}
