package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.UserVo;



public class UserDao {
	private DBConnection dbConnection;
	
	public UserDao(DBConnection dbConnection){
		this.dbConnection = dbConnection;
	}
	
	public void insert(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = dbConnection.getConnection();
			String sql =
					"INSERT INTO user VALUES(null,?,?,password(?),?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
		}catch(SQLException e){
			System.out.println("error:"+e);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(conn != null){
					conn.close();
				}
			}catch(SQLException e){
				System.out.println("error:"+e);
			}
		}
	}
}
