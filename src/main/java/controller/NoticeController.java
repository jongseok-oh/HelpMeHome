package controller;

import java.sql.SQLException;
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
		} else if (url.equals("/notice/notice.do")) {
			return notice(request, response);
		}else if (url.equals("/notice/notice_detail.do")) {
			return noticeDetail(request, response);
		} else if (url.equals("/notice/modify.do")) {
			return modify(request, response);
		} else if (url.equals("/notice/remove.do")) {
			return remove(request, response);
		}
		
		return null;
	}

	private PageInfo remove(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		boolean res = noticeService.deleteNotice(no);
		
		if(res) {
			request.setAttribute("msg", "공지 삭제에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "공지 삭제에 실패하였습니다.");			
		}
		return new PageInfo(true,"/notice/notice.do");
	}

	private PageInfo modify(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int no = Integer.parseInt(request.getParameter("no"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String date = request.getParameter("date");
		String info = request.getParameter("info");
		
		boolean res = noticeService.modifyNotice(new Notice(no,title,writer,date,info));
		
		// 3. move page by result
		if(res) {
			request.setAttribute("msg", "공지 수정에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "공지 수정에 실패하였습니다.");			
		}
		return new PageInfo(false,"/notice/notice.do");
	}

	private PageInfo noticeDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		System.out.println(1);
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println(no);
		Notice notice = noticeService.getNotice(no);
		request.setAttribute("notice", notice);
		return new PageInfo(true,"/notice/notice_detail.jsp");
	}

	private PageInfo notice(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<Notice> noticeList = noticeService.getNotices();
		request.setAttribute("noticeList", noticeList);
		
		return new PageInfo(true,"/notice/notice_form.jsp");
	}

	private PageInfo noticeForm(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("noticeForm");
		
		return new PageInfo(false,"/notice/notice_form.jsp");
	}		

}
