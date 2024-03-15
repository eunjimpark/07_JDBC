package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoService;
import edu.kh.todoList.model.service.TodoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//서비스 객체 생성
			 TodoService service = new TodoServiceImpl();
			
			//파라미터 얻어오기
			 String todoTitle = req.getParameter("todoTitle");
			 String todoContent = req.getParameter("todoContent");
			 
			 int result = service.addTodo(todoTitle, todoContent);
			 
			 String message = null;
			 
			 if(result > 0) message= "할일 추가 성공!";
			 else           message= "추가실패..";
			 
			 HttpSession session = req.getSession();
			 session.setAttribute("message", message);
			 
			 resp.sendRedirect("/");
		}
		catch(Exception e ){
			e.printStackTrace();
		}

	}
	

}
