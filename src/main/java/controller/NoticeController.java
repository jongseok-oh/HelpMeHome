package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.Notice;
import model.dto.PageInfo;
import model.dto.User;
import model.service.NoticeService;

public class NoticeController implements Controller{
	private NoticeService noticeService = new NoticeService();
	
	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		if (url.equals("/notice/notice_form.do")) {
			return noticeForm(request, response);
		}
		
		return null;
	}

	private PageInfo notice(HttpServletRequest request, HttpServletResponse response) {
		List<Notice> notices = noticeService.getNotices();
		request.setAttribute("noticeList", notices);
		
		return new PageInfo(true,"/notice/notice_form.jsp");
	}

	private PageInfo noticeForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("noticeForm");
		
		return new PageInfo(false,"/notice/notice_form.jsp");
	}		

}
