package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.dto.BaseAddress;
import model.service.ApartService;
import model.service.LocationService;

public class ApartController implements Controller{
	
	private ApartService apartService = new ApartService();
	private LocationService locationService = LocationService.getInstance();
	
	@Override
	public JSONArray handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/apart/getlist.do")) {
			return getBaseAddressListByPageNum(request,response);
		}else if(url.equals("/apart/getpagecnt.do")) {
			return getApartPageCnt(request, response);
		}
		return jarray;
	}
	
	private String getDongCode(HttpServletRequest request, HttpServletResponse response) {
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		return dongCode;
	}
	
	private JSONArray getBaseAddressListByPageNum(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		String dongCode = getDongCode(request, response);
		if(dongCode == null) return jsonArray;
		
		List<BaseAddress> addresses = apartService.getBaseAddressListByPageNum(dongCode, pageNum);
		
		
		for(BaseAddress b: addresses) {
			JSONObject jo = new JSONObject();
			
			jo.put("no", b.getNo());
			jo.put("sidoName", b.getSidoName());
			jo.put("gugunName", b.getGugunName());
			jo.put("dongName", b.getDongName());
			jo.put("dongCode", b.getDongCode());
			jo.put("lat", b.getLat());
			jo.put("lng", b.getLng());
			
			jsonArray.put(jo.toString());
		}
		return jsonArray;
	}
	
	private JSONArray getApartPageCnt(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jsonArray = new JSONArray();
		
		String dongCode = getDongCode(request, response);
		if(dongCode == null) {
			jsonArray.put(0);
			return jsonArray;
		}
		jsonArray.put(apartService.getApartPageCnt(dongCode));
		return jsonArray;
	}
}
