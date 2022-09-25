package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.dto.BaseAddress;
import model.service.ApartService;

public class ApartController implements Controller{
	
	private ApartService apartService = new ApartService();
	
	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/apart/getlist.do")) {
			return getBaseAddressListByPageNum(request,response);
		}
		return jarray;
	}
	
	
	private JSONArray getBaseAddressListByPageNum(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String dongCode = request.getParameter("dongCode");
		
		List<BaseAddress> addresses = apartService.getBaseAddressListByPageNum(dongCode, pageNum);
		
		for(BaseAddress b: addresses) {
			jsonArray.put(b);
		}
		return jsonArray;
	}
}
