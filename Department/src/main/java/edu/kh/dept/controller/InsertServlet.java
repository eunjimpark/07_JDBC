package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/department/insert")
public class InsertServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//JSP로 요청 위임해서 부서 추가 화면 보여주기
		String path = "/WEB-INF/views/insert.jsp";
		
		//요청발송자를 이용해서 요청위임하기
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			//서비스객체
			DepartmentService service = new DepartmentServiceImpl();
			//요청시 전달받은데이터 ==파라미터 얻어오기
			String deptId 		= req.getParameter("deptId"); // ("name속성값")
			String deptTitle  = req.getParameter("deptTitle"); 
			String locationId = req.getParameter("locationId"); 
			
			//다이나믹 웹 프로젝트 기본요청/응답 흐름
			
			/* 클라이언트 요청 
			 * -> Controller(Servlet, 요청에 따른 응답 화면 제어)
			 * <-> Service(비즈니스 로직 처리) 
			 * <-> DAO(DB 연결 객체)
			 * <-> DB
			 */
			Department dept = new Department (deptId, deptTitle, locationId);
					
			int result = service.insertDepartment(dept);
			
			String message = null;
			
			HttpSession session = req.getSession();
			
			if(result > 0) message = "부서추가 성공!";
			else           message = "부서추가 실패...";
			
			// 추가 동작 후 모든 부서 조회하는 Servlet을 재요청(redirect)
			// -> redirect는 재요청 -> request객체가 다시 만들어진다!!
			//											== 기존 request 객체가 사라짐!!
			// -> 그래서, 리다이렉트 시 데이터를 전달하고 싶으면
			//    request가 아니라 session에 담아서 전달해야 한다!!
			
			session.setAttribute("message", message);
			
			resp.sendRedirect("/department/selectAll");
			
		}catch(DepartmentInsertException e) {
			
			//제약조건 위배로 삽입실패
			req.setAttribute("errorMessage", e.getMessage());
			
			//에러페이지 포워드
			String path = "/WEB-INF/views/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
}
