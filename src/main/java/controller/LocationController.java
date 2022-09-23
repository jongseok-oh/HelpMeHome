package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.dto.PageInfo;
import model.service.LocationService;

public class LocationController {
	
	private LocationService locationService = LocationService.getInstance();
	
	public JSONArray ff(HttpServletRequest request, HttpServletResponse response) {
		
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/location/getgugun.do")) {
			jarray = getGugun(request, response);
		}else if(url.equals("/location/getdong.do")) {
			jarray = getDong(request, response);
		}
		
		return jarray;
	}
	
	private JSONArray getGugun(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String sidoName = request.getParameter("sidoName");
		List<String> gugunList = locationService.getGugunListBySido(sidoName);
		
		int idx = 1;
		for(String s: gugunList) {
			jsonArray.put(idx++, s);
		}
		return jsonArray;
	}
	
	private JSONArray getDong(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String gugunName = request.getParameter("gugunName");
		List<String> dongList = locationService.getDongListByGugun(gugunName);
		
		int idx = 1;
		for(String s: dongList) {
			jsonArray.put(idx++, s);
		}
		return jsonArray;
	}
}
