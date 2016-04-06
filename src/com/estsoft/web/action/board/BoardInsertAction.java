package com.estsoft.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDao;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 인증 유무 체크
		HttpSession session = request.getSession();
		if( session == null ) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		if( authUser == null ) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		
		
		String title = request.getParameter( "title" );
		String content = request.getParameter( "content" );
		String group_no = request.getParameter( "group_no" );
		String order_no = request.getParameter( "order_no" );
		String depth = request.getParameter( "depth" );

		BoardDao dao = new BoardDao( new MySQLWebDBConnection() );
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		
		session = request.getSession(true);
		UserVo vo1 = (UserVo)session.getAttribute("authUser");
		vo.setUser_no(vo1.getNo());
		System.out.println("group no : "+group_no);

		if(group_no != ""){
			System.out.println("group no : "+group_no);
			vo.setGroup_no( Long.parseLong( group_no ) );
			vo.setOrder_no( Long.parseLong( order_no ) + 1 );
			vo.setDepth( Long.parseLong( depth ) + 1 );
			
			dao.UpdateGroupOrder( vo );
		}
		
		dao.insert(vo);			
		WebUtil.redirect( request, response, request.getContextPath() + "/board" );
		
	}

}
