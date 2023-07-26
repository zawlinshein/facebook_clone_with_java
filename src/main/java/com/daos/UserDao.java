package com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.Part;

import com.connectDatabase.databaseConnection;
import com.dto.UserDto;
import com.handleImages.ImageCRUD;

public class UserDao implements Dao<UserDto, Part, Integer> {
	
	private final String SELECT_ALL_USER = "SELECT * FROM users";
	private final String INSERT_USER = "INSERT INTO users (name, password, email, profie_image) VALUES(?, ?, ?, ?)";
	private final String CHECK_USER = "SELECT * FROM users WHERE name=? AND password=?";
	private final String SELECT_BY_ID = "SELECT * FROM users WHERE id=?";
	private final String SELECT_USER_BY_COMMENT = "SELECT users.id, users.name, users.profie_image FROM users JOIN comments ON users.id = comments.user_id WHERE comments.post_id=?";	
	private final String profileUpload = "C:/Users/MSI/eclipse-workspace/javaProject/src/main/webapp/profileImage/";
	private final String UPDATE_USER = "UPDATE users SET name=?, password=?, email=?, profie_image=? WHERE id=?";
	
	@Override
	public void insert(UserDto user, Part file) throws SQLException {
		Connection con = null;
		
		try {
			con = databaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getImageName());
			int row = ps.executeUpdate();
			con.close();
			ImageCRUD.savephoto(user.getImageName(), file, profileUpload);
			if(row > 0) {
				System.out.println("user inserted into database " + row + " row");
			} else {
				System.out.println("can't insert into database");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		finally {
			con.close();
			System.out.println("connection closed");
		}
		
	}

	
	public int checkUser(String name, String password) throws SQLException {
		Connection con = null;
		int userId = 0;
		try {
			con = databaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(CHECK_USER);
			ps.setString(1, name);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userId = rs.getInt("id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		}
		return userId;
	}


	@Override
	public ArrayList<UserDto> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<UserDto> userList = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		Statement stm = null;
		try {
			con = databaseConnection.getConnection();
			stm = con.createStatement();
			rs = stm.executeQuery(SELECT_ALL_USER);
			while(rs.next()) {
				userList.add(new UserDto(rs.getInt("id"), rs.getString("name"), rs.getString("profie_image")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			rs.close();
			con.close();
			stm.close();
		}
		return userList;
	}


	@Override
	public UserDto selectById(Integer user_id) throws SQLException {
		ResultSet rs = null;
		UserDto user = null;
		Connection con = null;
		try {
			con = databaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new UserDto(rs.getInt("id"), rs.getString("name"), rs.getString("profie_image"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			rs.close();
			con.close();
		}
		return user;
	}
	
	public ArrayList<UserDto> selectUsersById (int id) throws SQLException {
		ArrayList<UserDto> users = new ArrayList<>();
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(SELECT_USER_BY_COMMENT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new UserDto(rs.getInt("id"), rs.getString("name"), rs.getString("profie_image")));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			con.close();
			rs.close();
		}
		
		return users;
	}


	@Override
	public void delete(Integer i) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void upDate(UserDto user) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			con = databaseConnection.getConnection();
			ps = con.prepareStatement(UPDATE_USER);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getImageName());
			ps.setInt(5, user.getId());
			int result = ps.executeUpdate();
			
			if(result > 0) {
				System.out.println("user updated successfully");
			} else {
				System.out.println("update user failed !!!");
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
	
	public UserDto selectUserInfo(int id) throws SQLException {
		ResultSet rs = null;
		UserDto user = null;
		Connection con = null;
		try {
			con = databaseConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new UserDto(rs.getInt("id"), rs.getString("name"), rs.getString("password"), rs.getString("email"), rs.getString("profie_image"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		} finally {
			rs.close();
			con.close();
		}
		return user;
	}

}
