package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.dto.BaseAddress;
import model.dto.PageInfo;
import model.dto.SidoCode;
import model.service.LocationService;

public class LocationController implements Controller{
	
	private LocationService locationService = LocationService.getInstance();

	@Override
	public Object handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/location/getsido.do")) {
			return getSidoList(request, response);
		}
		else if(url.equals("/location/getgugun.do")) {
			jarray  = getGugunList(request, response);
		}else if(url.equals("/location/getdong.do")) {
			jarray = getDongList(request, response);
		}else if(url.equals("/location/getcoordinate.do")) {
			jarray = getCoordinate(request, response);
		}
		return jarray;
	}


	
	private String getDongCode(HttpServletRequest request, HttpServletResponse response) {
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		System.out.println("sido = " + sidoName + " gugun = " + gugunName + " dong = "+dongName);
		
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		System.out.println(dongCode);
		return dongCode;
	}
	
	private JSONArray getSidoList(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		List<SidoCode> sidoList = locationService.getSidoList();

		for(SidoCode s: sidoList) {
			jsonArray.put(s.getSidoName());
		}
		return jsonArray;
	}
	
	
	private JSONArray getGugunList(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String sidoName = request.getParameter("sidoName");
		List<String> gugunList = locationService.getGugunListBySido(sidoName);
		
		for(String s: gugunList) {
			jsonArray.put(s);
		}
		return jsonArray;
	}
	
	private JSONArray getDongList(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		System.out.println("sidoName = " + sidoName + " gugunName = " + gugunName);
		List<String> dongList = locationService.getDongNameList(sidoName, gugunName);
		
		for(String s: dongList) {
			jsonArray.put(s);
		}
		return jsonArray;
	}
	
	private JSONArray getCoordinate(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		String dongCode = getDongCode(request, response);
		BaseAddress address = locationService.getBaseAddressByDongCode(dongCode);
		if(address != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("dongCode", address.getDongCode());
			jsonObject.put("lat", address.getLat());
			jsonObject.put("lng", address.getLng());
			jsonArray.put(jsonObject);
		}
		return jsonArray;
	}
}
