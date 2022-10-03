package controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import model.dto.BaseAddress;
import model.dto.Houseinfo;
import model.dto.PageInfo;
import model.dto.UserArea;
import model.service.AreaService;
import model.service.LocationService;

public class AreaController implements Controller{
	private AreaService areaService = new AreaService();
	private LocationService locationService = LocationService.getInstance();
	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		if(url.equals("/area/gwansimForm.do")){
			return gwansimForm(request,response);
		} else if(url.equals("/area/gwansimRegist.do")){
			return gwansimRegist(request,response);
		}else if(url.equals("/area/gwansimList.do")){
			return getUserAreaListByUserId(request,response);
		} else if(url.equals("/area/gwansimDelete.do")){
			return gwansimDelete(request,response);
		}
		return null;
	}

	private JSONObject gwansimDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		JSONObject jsonObject = new JSONObject();
		
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		
		boolean res = areaService.deleteUserArea(userId, dongCode);
		if(res) {
			System.out.println("area delete sucess");
			jsonObject.put("status", 201);
		}
		else {
			System.out.println("area delete fail");
			jsonObject.put("status", 409);
		}
		return jsonObject;
	}
	private JSONObject gwansimRegist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		//세션에서 String userId = request.getParameter("userId");
		//location        String dongCode = request.getParameter("dongCode");
		
		JSONObject jsonObject = new JSONObject();
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
	
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		//System.out.println("||||"+sidoName+","+gugunName+","+dongName);
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		
		System.out.println("gwansimRegist dongCode = " + dongCode);
		
		boolean res = areaService.registerUserArea(new UserArea(userId,dongCode));
		
		System.out.println("res = " +res);
		
		if(res) {
			System.out.println("area insert sucess");
			jsonObject.put("status", 201);
		}
		else {
			System.out.println("area insert fail");
			jsonObject.put("status", 409);
		}
		return jsonObject;
	}
	
	
	private JSONArray getUserAreaListByUserId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		JSONArray jarray = new JSONArray();
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("userId : "+userId);
		List<UserArea> userareas = areaService.getUserAreaListByUserId(userId);
		
		for(UserArea ua : userareas) {
			JSONObject jsonObject = new JSONObject();
			BaseAddress baseAddress = areaService.getSiGuDong(ua.getDongCode());
			jsonObject.put("sidoName", baseAddress.getSidoName());
			jsonObject.put("gugunName", baseAddress.getGugunName());
			jsonObject.put("dongName", baseAddress.getDongName());
			jarray.put(jsonObject);
			
		}
		return jarray;
	}
	
	
	

	private PageInfo gwansimForm(HttpServletRequest request, HttpServletResponse response) {
		return new PageInfo(false,"/area/gwansim.jsp");
	}

}
