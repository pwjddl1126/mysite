package com.estsoft.web.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.web.action.Action;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String kwd = request.getParameter( "kwd" );
		
		System.out.println("kwd : " + kwd);
		BoardDao dao = new BoardDao( new MySQLWebDBConnection() );
		List<BoardVo> list = null;
		if(kwd != null){
			list = dao.getList(kwd);
		}else{
			list = dao.getList();
		}
		
		// 포워딩전에 JSP로 보낼 데이터를 request범위(scope)에 저장한다.
		request.setAttribute( "list", list );
		
		// forwarding (request 확장, request dispatcher )
		RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/board/list.jsp"  );
		rd.forward( request, response );	

	}

}
