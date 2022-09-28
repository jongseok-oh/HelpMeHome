package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dto.PageInfo;

public class AreaController implements Controller{

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		if(url.equals("/area/gwansimForm.do")){
			return gwansimForm(request,response);
		}
		return null;
	}

	private PageInfo gwansimForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/area/gwansim.jsp");
	}

}
