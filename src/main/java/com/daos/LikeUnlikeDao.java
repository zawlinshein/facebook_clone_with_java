package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.Part;

import com.connectDatabase.databaseConnection;
import com.dto.LikeUnlikeDto;

public class LikeUnlikeDao implements Dao<LikeUnlikeDto, Part, Integer> {
	
	private final String INSERT = "INSERT INTO like_unlike (post_id, user_id) VALUES (?, ?)";
	private final String CHECK = "SELECT * FROM like_unlike WHERE post_id=? AND user_id=?";
	private final String DELETE_LIKE = "DELETE FROM like_unlike WHERE post_id=? AND user_id=?";
	private final String COUNT_BY_ID = "SELECT COUNT(post_id) AS count FROM like_unlike WHERE post_id=?";
	private final String SELECT_ALL = "SELECT post_id FROM like_unlike GROUP BY post_id";
	private final String DELETE_ALL_LIKE = "DELETE FROM like_unlike WHERE post_id=?";

    @Override
	public LikeUnlikeDto selectById(Integer postId) throws SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ArrayList<LikeUnlikeDto> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<LikeUnlikeDto> allLike = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(SELECT_ALL);
			rs = ps.executeQuery();
			while(rs.next()) {
				allLike.add(new LikeUnlikeDto(rs.getInt("post_id")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} 
		
		return allLike;
	}

	@Override
	public void insert(LikeUnlikeDto o, Part p) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public boolean check(LikeUnlikeDto likeUnlike) throws SQLException {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		boolean result = false;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(CHECK);
			ps.setInt(1, likeUnlike.getPostId());
			ps.setInt(2, likeUnlike.getUserId());
			rs = ps.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			ps.close();
			rs.close();
		}
		return result;
	}
	
	public void insert(LikeUnlikeDto likeUnlike) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(INSERT);
			ps.setInt(1, likeUnlike.getPostId());
			ps.setInt(2, likeUnlike.getUserId());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			ps.close();
		}
	}
	
	public void delete(LikeUnlikeDto likeUnlike) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(DELETE_LIKE);
			ps.setInt(1, likeUnlike.getPostId());
			ps.setInt(2, likeUnlike.getUserId());
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
	
	public int count(int postId) throws SQLException {
		int result = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(COUNT_BY_ID);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
			rs.close();
		}
		return result;
	}

	@Override
	public void delete(Integer i) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(DELETE_ALL_LIKE);
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
	public void upDate(LikeUnlikeDto o) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
}
