package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/department/update")
public class UpdateServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻어오기
		String deptId = req.getParameter("deptId");
		
		try {
			//서비스객체생성
			DepartmentService service = new DepartmentServiceImpl();
			
			//deptId가 일치하는 1행 조회 서비스 호출
			Department dept = service.selectOne(deptId);
			
			//조회결과 없을경우
			if(dept==null) {
				req.getSession().setAttribute("message","해당부서가 존재하지않아여");
				resp.sendRedirect("/department/selectAll");
			}else {//조회결과있을경우
				req.setAttribute("dept",dept);
				String path = "/WEB-INF/views/update.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//부서수정
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			// 서비스 객체 생성
			DepartmentService service = new DepartmentServiceImpl();
			
			// 전달 받은 파라미터를 모두 얻어와 Department 객체에 저장
			String deptId 		= req.getParameter("deptId");
			String deptTitle 	= req.getParameter("deptTitle");
			String locationId = req.getParameter("locationId");
			
			Department dept = new Department(deptId, deptTitle, locationId);
			
			//부서 수정 서비스 호출 후 결과 반환받기
			int result = service.updateDepartment(dept);
			
			//수정결과에 따라 메세지 지정
			String message = null;
			if(result > 0) message = "부서수정성공";
			else           message = "부서 수정 실패";
			
			//메세지를 세션에 속성으로 추가
			req.getSession().setAttribute("message",message);
			
			//전체 부서 조회 재요청
			resp.sendRedirect("/department/selectAll");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	
	
}
