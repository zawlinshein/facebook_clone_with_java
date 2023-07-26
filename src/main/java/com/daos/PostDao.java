	package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import com.connectDatabase.databaseConnection;
import com.dto.PostDto;
import com.handleImages.ImageCRUD;

public class PostDao implements Dao<PostDto, Part, Integer> {
	
	private final String INSERT_POST = "INSERT INTO posts (user_id, description, post_image) VALUES (?, ?, ?)";
	private final String SELECT_BY_ID = "SELECT * FROM posts WHERE id=?";
	private final String SELECT_ALL = "SELECT * FROM posts ORDER BY posts.id DESC";
	private final String SELECT_ALL_BY_ID = "SELECT * FROM posts WHERE user_id=? ORDER BY posts.id DESC";
	private final String POST_UPLOAD_PATH = "C:/Users/MSI/eclipse-workspace/javaProject/src/main/webapp/postImages/";
	private final String DELETE_POST = "DELETE FROM posts WHERE id=?";
	private final String Upload_POST = "UPDATE posts SET description=?, post_image=? WHERE id=?";
	
	@Override
	public void insert(PostDto post, Part file) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(INSERT_POST);
			ps.setInt(1, post.getUserId());
			ps.setString(2, post.getDescription());
			ps.setString(3, post.getPostImageName());
			int result = ps.executeUpdate();
			ImageCRUD.savephoto(post.getPostImageName(), file, POST_UPLOAD_PATH);
			if(result > 0) {
				System.out.println("post has created sucessfully!");
			} else {
				System.out.println("creating post failed!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			ps.close();
		}
		
	}	

	@Override
	public PostDto selectById(Integer post_id) throws SQLException {
		ResultSet rs = null;
		Connection con = null;
		PostDto post = null;
		try {
			con = databaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, post_id);
			rs = ps.executeQuery();
			if(rs.next()) {	
				post = new PostDto(rs.getInt("id"),rs.getInt("user_id"), rs.getString("description"), rs.getString("post_image"));
			} else {
				System.out.println("post does not exist!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}finally {
			rs.close();
			con.close();
		}
		return post;
	}


	@Override
	public ArrayList<PostDto> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<PostDto> posts = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		Statement stm = null;
		try {
			con = databaseConnection.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(SELECT_ALL);
			while (rs.next()) {
				posts.add(new PostDto(rs.getInt("id"), rs.getInt("user_id"), rs.getString("description"), rs.getString("post_image")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
			stm.close();
		}
		
		return posts;
	}
	
	public ArrayList<PostDto> selectPostsById(int id) throws SQLException {
		ArrayList<PostDto> posts = new ArrayList<>();
		Connection con = null;
		ResultSet rs = null;
		try {
			con = databaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_ALL_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				posts.add(new PostDto(rs.getInt("id"),rs.getInt("user_id"), rs.getString("description"),rs.getString("post_image")));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			con.close();
			rs.close();
		}
		return posts;
	}

	@Override
	public void delete(Integer i) throws SQLException {
		Connection con = null;
		PreparedStatement select = null;
		PreparedStatement delete = null;
		CommentDao commentDao = new CommentDao();
		LikeUnlikeDao likeUnlikeDao = new LikeUnlikeDao();
		ResultSet rs = null;
		String imageName = "";
		
		try {
			con = databaseConnection.getConnection();
			select = con.prepareStatement(SELECT_BY_ID);
			delete = con.prepareStatement(DELETE_POST);
			select.setInt(1, i);
			delete.setInt(1, i);
			rs = select.executeQuery();
			while(rs.next()) {
				imageName = rs.getString("post_image");
			}
			ImageCRUD.deletePhoto(POST_UPLOAD_PATH, imageName);
			delete.executeUpdate();
			commentDao.delete(i);
			likeUnlikeDao.delete(i);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			select.close();
			delete.close();
		}
		
	}

	@Override
	public void upDate(PostDto post) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(Upload_POST);
			ps.setString(1, post.getDescription());
			ps.setString(2, post.getPostImageName());
			ps.setInt(3, post.getUserId());
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

}
