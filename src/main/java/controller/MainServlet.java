package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dto.PageInfo;
import model.dto.User;
import model.service.UserService;

@WebServlet(loadOnStartup = 1, urlPatterns = { "*.do", "*.ssafy" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private UserService userService = new UserService();
	private String root;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = getServletContext();
		root  = application.getContextPath();
		application.setAttribute("root", root);
		System.out.println("root set...");
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		System.out.println("url:" + url);

//		if(url.startsWith("/user")) {
//			HttpSession session = request.getSession();
//			if(session.getAttribute("userId") == null) {
//				System.out.println("로그인 x");
//				response.sendRedirect(root+"/user/login.do");
//				return;
//			}
//		}
		
		
		PageInfo pageInfo = null;
		try {
			if (url.equals("/user/signup_form.do")) {
				pageInfo = signupForm(request, response);
			} else if (url.equals("/user/register.do")) {
				pageInfo = register(request, response);
			} else if (url.equals("/user/login.do")) {
				pageInfo = login(request, response);
			} else {				
				pageInfo = index(request, response);				
			}
			
			if(pageInfo.isForward()) {
				request.getRequestDispatcher(pageInfo.getPage()).forward(request, response);
				return;
			}else {
				response.sendRedirect(request.getContextPath()+pageInfo.getPage());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("../error.jsp").forward(request, response);
			return;
		}


	}



	private PageInfo login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(1);
		String userId = request.getParameter("userId");
		String passWord = request.getParameter("passWord");
		System.out.println(userId+","+passWord);
		
		String name = userService.login(userId, passWord);
		System.out.println(2);
		if(name != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("userName", name);
		}
		System.out.println(3);
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
		//일단 예외처리안함

		return new PageInfo(false,"/index.do");
	}



	private PageInfo signupForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/user/registor.jsp");
	}



	private PageInfo index(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/index.jsp");
	}

}
