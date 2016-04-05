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

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String no = request.getParameter( "no" );
		
		if( no == null  ) {
			WebUtil.redirect( request, response, request.getContextPath() + "/board" );
			return;
		}
		
		Long board_no = Long.parseLong( no );
		
		// DAO 생성
		BoardDao dao = new BoardDao( new MySQLWebDBConnection() ) ;
		
		dao.raiseCount(board_no);
		BoardVo vo = dao.get(  board_no );
		
		request.setAttribute( "vo", vo );
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp" );
	}

}
