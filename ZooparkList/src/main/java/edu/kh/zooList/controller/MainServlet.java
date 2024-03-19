package edu.kh.zooList.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.zooList.model.dto.Zoo;
import edu.kh.zooList.model.service.ZooService;
import edu.kh.zooList.model.service.ZooServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			ZooService service = new ZooServiceImpl();
			
			Map<String, Object> map = service.selectAll();
			
			List<Zoo> zooList = (List<Zoo>)map.get("zooList");
			int animalCount = (int)map.get("animalCoumt");
		
			
			req.setAttribute("zooList", zooList);
			req.setAttribute("animalCount", animalCount);
			
			
			
			
			String path = "/WEB-INF/views/main.jsp";
			req.getRequestDispatcher(path).forward(req,resp);
			
	
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	
}
