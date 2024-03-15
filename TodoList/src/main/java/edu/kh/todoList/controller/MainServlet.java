package edu.kh.todoList.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// "" == 최상위 주소 요청
@WebServlet("")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			TodoService service = new TodoServiceImpl();
			
			//할일목록 조회 + 완료된 할일목록 개수 서비스호출후 결과반환받기
			Map<String, Object > map = service.selectAll();
			
			//map에 담긴 데이터분리
			List<Todo> todoList = (List<Todo>)map.get("todoList");
			int completeCount = (int)map.get("completeCount");
			
			//분리된 데이터를 리퀘스트 객체에 속성으로 추가
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);
			
			
			String path = "/WEB-INF/views/main.jsp";
			
			//메인.jsp로 포워드
			req.getRequestDispatcher(path).forward(req, resp);
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}
