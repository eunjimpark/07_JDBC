package edu.kh.dept.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.dept.service.DepartmentServiceImpl;
import edu.kh.dept.service.DepartmentService;
import edu.kh.model.dto.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/department/selectAll")
public class SelectAllServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Service 객체 생성
			DepartmentService service = new DepartmentServiceImpl();
			
			// 모든 부서 조회 Service 호출 후 결과 반환 받기
			List<Department> deptList = service.selectAll();
			
			
			
			req.setAttribute("deptList", deptList);
			
			String path = "/WEB-INF/views/selectAll.jsp";
			
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
