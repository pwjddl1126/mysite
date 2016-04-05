package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.BoardVo;
import com.estsoft.mysite.vo.GuestbookVo;
import com.estsoft.mysite.vo.UserVo;

public class BoardDao {
	private DBConnection dbConnection;

	public BoardDao(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	//get
	public BoardVo get(Long boardNo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		BoardVo vo = new BoardVo();
		try{
			conn = dbConnection.getConnection();
			String sql = "SELECT no, title, content,"
					+ " group_no, order_no, depth, user_no FROM board WHERE no = ?";
			pstmt = conn.prepareStatement( sql );
			
			pstmt.setLong( 1, boardNo );
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				Long no = rs.getLong( 1 );
				String title = rs.getString( 2 );
				String content = rs.getString( 3 );
				Integer group_no = rs.getInt( 4 );
				Integer order_no = rs.getInt( 5 );
				Integer depth = rs.getInt( 6 );
				Long user_no = rs.getLong(7);

				vo = new BoardVo();
				vo.setNo( no );
				vo.setTitle( title );
				vo.setContent( content );
				vo.setGroup_no( group_no );
				vo.setOrder_no( order_no );
				vo.setDepth( depth );
				vo.setUser_no( user_no );
			}
			return vo;
		}catch(SQLException ex){
			System.out.println("error:" + ex);
			return vo;
		}finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	// insert
	public void insert(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "INSERT INTO board VALUES(null, ?, ?,now(), 0, (select ifnull(max(group_no),0)+1 from board as b),1,0,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUser_no());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// update
	public void update(BoardVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "UPDATE board SET title = ?, content = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	

	// delete
	public void delete(BoardVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "DELETE FROM board WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getNo());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	// list
	public List<BoardVo> getList(String kwd) {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();

			String sql = "SELECT b.no, b.title, b.content, DATE_FORMAT( b.reg_date, '%Y-%m-%d %p %h:%i:%s' ), b.viewcount,"
					+ " b.group_no, b.order_no, b.depth, b.user_no, a.name from user a, board b WHERE a.no = b.user_no"
					+ " AND (b.title LIKE ? OR b.content LIKE ?) ORDER BY b.reg_date desc";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.setString(2, "%"+kwd+"%");

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String reg_date = rs.getString(4);
				Long viewcount = rs.getLong(5);
				Long group_no = rs.getLong(6);
				Long order_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setReg_date(reg_date);
				vo.setViewcount(viewcount);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setName(name);

				list.add(vo);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return list;

	}
	
	public List<BoardVo> getList() {
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT b.no, b.title, b.content, DATE_FORMAT( b.reg_date, '%Y-%m-%d %p %h:%i:%s' ), b.viewcount,"
					+ " b.group_no, b.order_no, b.depth, b.user_no, a.name from user a, board b WHERE a.no = b.user_no ORDER BY reg_date desc";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String reg_date = rs.getString(4);
				Long viewcount = rs.getLong(5);
				Long group_no = rs.getLong(6);
				Long order_no = rs.getLong(7);
				Long depth = rs.getLong(8);
				Long user_no = rs.getLong(9);
				String name = rs.getString(10);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setReg_date(reg_date);
				vo.setViewcount(viewcount);
				vo.setGroup_no(group_no);
				vo.setOrder_no(order_no);
				vo.setDepth(depth);
				vo.setUser_no(user_no);
				vo.setName(name);

				list.add(vo);
			}
		} catch (SQLException ex) {
			System.out.println("error: " + ex);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return list;
	}
	
	//raise viewcount
	public void raiseCount(long board_no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbConnection.getConnection();
			String sql = "UPDATE board SET viewcount = viewcount+1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, board_no);

			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("error:" + ex);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
