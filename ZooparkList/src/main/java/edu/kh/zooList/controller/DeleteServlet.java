package edu.kh.zooList.controller;

import java.io.IOException;

import edu.kh.zooList.model.service.ZooService;
import edu.kh.zooList.model.service.ZooServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/zoo/delete")
public class DeleteServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			// 서비스 객체 생성
			ZooService service = new ZooServiceImpl();
			
			// 제출된 파라미터 얻어오기
			int zooNo = Integer.parseInt( req.getParameter("zooNo")); 
			
			// 서비스 메서드 호출 후 결과 반환 받기
			int result = service.deleteZoo(zooNo);
			
			
			// 서비스 결과에 따라서
			// Session에 "삭제 성공", "삭제 실패" 메시지를 속성을 추가
			
			String message =  null;
			HttpSession session = req.getSession();
			
			if(result > 0)	message = "삭제 성공";
			else						message = "삭제 실패";
			
			session.setAttribute("message", message);
			
			
			// 전체 부서 조회를 재요청
			resp.sendRedirect("/zoo/selectAll");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		
		}
	
	}

}



