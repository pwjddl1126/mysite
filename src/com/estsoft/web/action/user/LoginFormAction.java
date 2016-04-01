package com.estsoft.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class LoginFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//쿠키받기
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
			for(Cookie cookie : cookies){
				System.out.println(cookie.getName()+"."+cookie.getValue());
			}
		}
		WebUtil.forward(request,response,"/WEB-INF/views/user/loginform.jsp");
	}

}
