package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.dto.BaseAddress;
import model.dto.Houseinfo;
import model.service.ApartService;
import model.service.LocationService;

public class ApartController implements Controller{
	
	private ApartService apartService = new ApartService();
	//private LocationService locationService = LocationService.getInstance();
	
	@Override
	public JSONArray handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getServletPath();
		JSONArray jarray = null;
		
		if(url.equals("/apart/getlist.do")) {
			return getHouseinfoListByDongCode(request,response);
		}
		return jarray;
	}

	private JSONArray getHouseinfoListByDongCode(HttpServletRequest request, HttpServletResponse response) {
		JSONArray jarray = new JSONArray();
		String dongCode = request.getParameter("dongCode");
		List<Houseinfo> houseinfos = apartService.getHouseinfoListByDongCode(dongCode);
		
		for(Houseinfo info : houseinfos) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("aptCode", info.getAptCode());
			jsonObject.put("aptName", info.getAptName());
			jsonObject.put("buildYear", info.getBuildYear());
			jsonObject.put("lat", info.getLat());
			jsonObject.put("lng", info.getLng());
			jarray.put(jsonObject);
		}
		return jarray;
	}
}
