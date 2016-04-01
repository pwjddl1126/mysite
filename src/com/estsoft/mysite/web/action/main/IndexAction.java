package com.estsoft.mysite.web.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class IndexAction implements Action{

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		Cookie cookie = new Cookie("testCookie","Hello World"); //이름,데이터
		cookie.setMaxAge(60*60*24*1);
		cookie.setPath("/mysite/");//mysite로 접근하는 모든 방법에 쿠키를 굽기
		
		response.addCookie(cookie);//response에 쿠키를 굽기
		
		WebUtil.forward(request,response,"/WEB-INF/views/main/index.jsp");
	}
}
