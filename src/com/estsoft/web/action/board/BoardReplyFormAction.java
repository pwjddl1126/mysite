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

public class BoardReplyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		Long no = Long.parseLong( request.getParameter( "no" ));

		BoardDao dao = new BoardDao( new MySQLWebDBConnection() );
		BoardVo vo = dao.get(no);
		
		request.setAttribute( "vo", vo );
		
		WebUtil.forward(request,response,"/WEB-INF/views/board/reply.jsp");

	}

}
