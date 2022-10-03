package controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import model.dto.PageInfo;
import model.dto.User;
import model.service.LocationService;
import model.service.UserService;

@WebServlet(loadOnStartup = 1, urlPatterns = { "*.do", "*.ssafy" })
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserController userController = new UserController();
	private HomeController homeController = new HomeController();
	private LocationController locationController = new LocationController();
	private ApartController apartController = new ApartController();
	private NoticeController noticeController = new NoticeController();
	private AreaController areaController = new AreaController();
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
		System.out.println("Post");
		process(request, response);
	}
	
	protected void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getServletPath();
		System.out.println("url:" + url);

		if(url.startsWith("/location") || url.startsWith("/apart")) {
			HttpSession session = request.getSession();
			if(session.getAttribute("userId") == null) {
				System.out.println("로그인 x");
				response.sendRedirect(root+"/user/signup_form.do"); //임시
				return;
			}
		}
		
		
		
		Object result = null;
		
		try {
			Controller controller = null;
			if(url.startsWith("/user")) {
				controller = userController;
			}else if(url.startsWith("/location")) {
				controller = locationController;
			}else if(url.startsWith("/apart")) {
				controller = apartController;
			}else if(url.startsWith("/notice")) {
				controller = noticeController;
			}else if(url.startsWith("/area")) {
				controller = areaController;
			}
			else {
				controller = homeController;
			}
		
			if(controller != null) {
				 result = controller.handleRequest(request,response);
			}

			if(result instanceof PageInfo) {
				PageInfo pageInfo = (PageInfo)result;
				if(pageInfo.isForward()) {
					request.getRequestDispatcher(pageInfo.getPage()).forward(request, response);
					return;
				}else {
					response.sendRedirect(request.getContextPath()+pageInfo.getPage());
				}
			} else if(result instanceof JSONArray){
				JSONArray jarray = (JSONArray) result;
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(jarray.toString()); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(result instanceof PageInfo) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("../error.jsp").forward(request, response);
				return;
			}else {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}
		}


	}



	

}

