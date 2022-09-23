package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.PageInfo;
import model.dto.User;
import model.service.UserService;

public class UserController implements Controller{
	
	private UserService userService = new UserService();
	
	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		if (url.equals("/user/signup_form.do")) {
			return signupForm(request, response);
		} else if (url.equals("/user/register.do")) {
			return register(request, response);
		} else if (url.equals("/user/login.do")) {
			return login(request, response);
		} else if (url.equals("/user/fix_form.do")) {
			return fixForm(request, response);
		}  else if (url.equals("/user/logout.do")) {
			return logout(request, response);
		} else if (url.equals("/user/modify.do")) {
			return modify(request, response);
		} else if (url.equals("/user/delete.do")) {
			return delete(request, response);
		} 	
		
		return null;
	}
		private PageInfo delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userId");
		
		boolean res = userService.deleteUser(userId);
		
		if(res) {
			request.setAttribute("msg", "유저 삭제에 성공하였습니다.");
			return new PageInfo(true,"/user/logout.do");
		}else {
			request.setAttribute("msg", "유저 삭제에 실패하였습니다.");
			return new PageInfo(true,"user/fix_form.do");
		}
		
	}
	
	
	private PageInfo modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		
		User user = new User(userId,passWord,name,phoneNumber);
		boolean res = userService.modifyUser(user);
		
		if(res) {
			request.setAttribute("msg", "유저 수정에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "유저 수정에 실패하였습니다.");			
		}
		return new PageInfo(true,"/index.do");
	}

	private PageInfo logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		
		return new PageInfo(false,"/index.do");
	}

	

	private PageInfo login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		System.out.println(userId+","+passWord);
		
		String name = userService.login(userId, passWord);
		
		if(name != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userName", name);
		}
		
		return new PageInfo(false,"/index.do");
		
		//에러 처리안함1
		
		
		
	}



	private PageInfo register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phoneNumber");
		User user = new User(userId,passWord,name,phoneNumber);
		
		boolean res = userService.registerUser(user);
		if(res) {
			request.setAttribute("msg", "유저 등록에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "유저 등록에 실패하였습니다.");			
		}

		return new PageInfo(false,"/index.do");
	}



	private PageInfo signupForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/user/registor.jsp");
	}
	private PageInfo fixForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/user/fix.jsp");
	}


	
}
