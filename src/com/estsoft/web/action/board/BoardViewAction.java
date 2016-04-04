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
		
		Long boardNo = Long.parseLong( no );
		
		// DAO 생성
		BoardDao boardDao = new BoardDao( new MySQLWebDBConnection() ) ;
		
//		boardDao.updateHits( boardNo );
		BoardVo boardVo = boardDao.get(  boardNo );
		
		request.setAttribute( "vo", boardVo );
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp" );
	}

}
