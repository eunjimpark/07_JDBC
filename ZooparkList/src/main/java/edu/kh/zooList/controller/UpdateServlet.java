package edu.kh.zooList.controller;

import java.io.IOException;

import edu.kh.zooList.model.dto.Zoo;
import edu.kh.zooList.model.service.ZooService;
import edu.kh.zooList.model.service.ZooServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/zoo/update")
public class UpdateServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			ZooService service = new ZooServiceImpl(); // 서비스 객체 생성
			
			// 파라미터 얻어오기(+ int로 변환)
			int ZooNo = Integer.parseInt(req.getParameter("ZooNo"));
			
			// 상세 조회 서비스 호출
		  Zoo zoo = service.selectZoo(ZooNo);
			
			if(zoo != null) { // 존재 == 수정 가능
				req.setAttribute("zoo", zoo);
				String path = "/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
				
			} else { // 존재X == 수정 불가
				req.getSession().setAttribute("message", "없는동물이에요");
				resp.sendRedirect("/"); // 메인 페이지로 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
