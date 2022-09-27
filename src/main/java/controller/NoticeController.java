package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.PageInfo;
import model.dto.User;
import model.service.NoticeService;

public class NoticeController implements Controller{
	private NoticeService noticeService = new NoticeService();
	
	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		if (url.equals("/notice/notice_form.do")) {
			System.out.println("notice_form");
			return noticeForm(request, response);
		} 
		
		return null;
	}

	private PageInfo noticeForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("noticeForm");
		
		return new PageInfo(false,"/notice/notice_form.jsp");
	}		

}
