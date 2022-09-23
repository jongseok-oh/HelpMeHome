package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import model.dto.PageInfo;
import model.dto.SidoCode;
import model.service.LocationService;

public class LocationController implements Controller{
	
	private LocationService locationService = LocationService.getInstance();
	
	@Override
	public JSONArray handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/location/getsido.do")) {
			return getSido(request, response);
		}
		else if(url.equals("/location/getgugun.do")) {
			return getGugun(request, response);
		}else if(url.equals("/location/getdong.do")) {
			return getDong(request, response);
		}
		return jarray;
	}
	
	private JSONArray getSido(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		List<SidoCode> sidoList = locationService.getSidoList();

		for(SidoCode s: sidoList) {
			jsonArray.put(s.getSidoName());
		}
		return jsonArray;
	}
	
	
	private JSONArray getGugun(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String sidoName = request.getParameter("sidoName");
		List<String> gugunList = locationService.getGugunListBySido(sidoName);
		
		for(String s: gugunList) {
			jsonArray.put(s);
		}
		return jsonArray;
	}
	
	private JSONArray getDong(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String gugunName = request.getParameter("gugunName");
		List<String> dongList = locationService.getDongListByGugun(gugunName);
		
		for(String s: dongList) {
			jsonArray.put(s);
		}
		return jsonArray;
	}

	
}
