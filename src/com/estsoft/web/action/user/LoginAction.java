package com.estsoft.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setEmail(email);
		vo.setPassword(password);
		
		UserDao dao = new UserDao( new MySQLWebDBConnection());
		UserVo authUser = dao.get(vo);
		
		System.out.println(authUser);
		
		if(authUser == null){
			//비밀번호나 이메일 틀림
			//1. rediret방식
//			WebUtil.redirect(request, response, "mysite/user?a=loginform&result=fail");
			//2. forward방식
			WebUtil.forward(request,response,"/WEB-INF/views/user/loginform_fail.jsp");
			return ;
		}
		
		//인증성공 ( 로그인처리 )
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		//메인으로 리다이렉트
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
