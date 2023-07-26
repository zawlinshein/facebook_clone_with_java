package com.dto;

public class PostDto {
	private int id;
	private int userId;
	private String description;
	private String postImageName;
	private String userName;
	private String userImage;
	
	public PostDto(int userId, String description, String postImageName) {
		this.userId = userId;
		this.description = description;
		this.postImageName = postImageName;
	}

	public PostDto(int id, int user_id, String description, String postImageName) {
		this.id = id;
		this.userId = user_id;
		this.description = description;
		this.postImageName = postImageName;
	}
	
	public	PostDto(int id, int user_id, String description, String postImageName, String userName, String userImage) {
		this.id = id;
		this.userId = user_id;
		this.description = description;
		this.postImageName = postImageName;
		this.userName = userName;
		this.userImage = userImage;
	}
	
	@Override
	public String toString() {
		return "PostDto [id=" + id + ", userId=" + userId + ", description=" + description + ", postImageName="
				+ postImageName + ", userName=" + userName + ", userImage=" + userImage + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostImageName() {
		return postImageName;
	}

	public void setPostImageName(String postImageName) {
		this.postImageName = postImageName;
	}
}
