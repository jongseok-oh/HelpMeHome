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
			gwansimRegist(request,response);
		}else if(url.equals("/area/gwansimList.do")){
			return getUserAreaListByUserId(request,response);
		} else if(url.equals("/area/gwansimDelete.do")){
			gwansimDelete(request,response);
		}
		return null;
	}

	private void gwansimDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		
		boolean res = areaService.deleteUserArea(userId, dongCode);
		if(res) {
			request.setAttribute("msg", "관심 목록 삭제에 성공하였습니다.");
		}else {
			request.setAttribute("msg", "관심 목록 삭제에 실패하였습니다.");			
		}
		
		
	}

	private void gwansimRegist(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		//세션에서 String userId = request.getParameter("userId");
		//location        String dongCode = request.getParameter("dongCode");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
		String sidoName = request.getParameter("sidoName");
		String gugunName = request.getParameter("gugunName");
		String dongName = request.getParameter("dongName");
		
		String dongCode = locationService.getDongCode(sidoName, gugunName, dongName);
		boolean res = areaService.registerUserArea(new UserArea(userId,dongCode));
		
		
		
	}
	
	private JSONArray getUserAreaListByUserId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		JSONArray jarray = new JSONArray();
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		
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
