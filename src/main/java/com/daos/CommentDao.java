package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.Part;

import com.connectDatabase.databaseConnection;
import com.dto.CommentDto;

public class CommentDao implements Dao<CommentDto, Part, Integer> {

	private final String INSERT_COMMENT = "INSERT INTO comments (post_id, user_id, comments) VALUES(?, ?, ?)";
	private final String SELECT_COMMENTS_BY_ID = "SELECT * FROM comments WHERE post_id=? ORDER BY comments.id DESC";
	private final String DELETE_ALL_COMMENT = "DELETE FROM comments WHERE post_id=?";
	
	@Override
	public void insert(CommentDto comment, Part p) throws SQLException {
		// TODO Auto-generated method stub
	}

	@Override
	public CommentDto selectById(Integer i) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<CommentDto> selectAll(int id) throws SQLException {
		ArrayList<CommentDto> comments = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(SELECT_COMMENTS_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				comments.add(new CommentDto(rs.getInt("id"), rs.getInt("post_id"), rs.getInt("user_id"), rs.getString("comments")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			rs.close();
			ps.close();
		}
		
		return comments;
	}

	@Override
	public ArrayList<CommentDto> selectAll() throws SQLException {	
		return null;
	}
	
//	insert method here
	
	public boolean insert(CommentDto comment) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(INSERT_COMMENT);
			ps.setInt(1, comment.getPostId());
			ps.setInt(2, comment.getUserId());
			ps.setString(3, comment.getComment());
			int row = ps.executeUpdate();
			result = row > 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			ps.close();
		}
		return result;
	}

	@Override
	public void delete(Integer i) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(DELETE_ALL_COMMENT);
			ps.setInt(1, i);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
		}
		
	}

	@Override
	public void upDate(CommentDto o) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
