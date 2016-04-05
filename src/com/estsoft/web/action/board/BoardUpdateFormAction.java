package com.estsoft.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//넘버가 넘어옴
		Long no = Long.parseLong( request.getParameter( "no" ));
		
		BoardDao dao = new BoardDao( new MySQLWebDBConnection() );
		BoardVo vo = dao.get(no);
		
		System.out.println("vo no : "+vo.getNo());
		System.out.println("vo title : "+vo.getTitle());
		System.out.println("vo content : "+vo.getContent());
		
		
		request.setAttribute( "vo", vo );
		
		WebUtil.forward(request,response,"/WEB-INF/views/board/modify.jsp");
	}

}
