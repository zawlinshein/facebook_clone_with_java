package com.dto;

public class CommentDto {
	private int id;
	private int postId;
	private int userId;
	private String comment;
	
	public CommentDto(int postId, int userId, String comment) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.comment = comment;
	}
	
	public CommentDto(int id, int postId, int userId, String comment) {
		super();
		this.id = id;
		this.postId = postId;
		this.userId = userId;
		this.comment = comment;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPostId() {
		return postId;
	}
	
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", postId=" + postId + ", userId=" + userId + ", comment=" + comment + "]";
	}
	
}
