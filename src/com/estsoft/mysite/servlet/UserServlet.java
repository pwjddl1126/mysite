package com.estsoft.mysite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;
import com.estsoft.web.action.user.UserActionFactory;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 현재 데이터 인코딩
		
		String actionName = request.getParameter("a");
		ActionFactory actionFactory = new UserActionFactory();
		Action action = actionFactory.getAction(actionName);
		
		action.execute(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
