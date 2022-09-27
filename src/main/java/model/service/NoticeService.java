package model.service;

import java.util.ArrayList;
import java.util.List;

import model.dao.NoticeDAO;
import model.dto.Notice;

public class NoticeService {
	private NoticeDAO noticeDao = new NoticeDAO();

	public List<Notice> getNotices() {
		return noticeDao.getNotices();
	}
}
