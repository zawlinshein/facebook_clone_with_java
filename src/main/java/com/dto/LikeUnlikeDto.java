package com.dto;

public class LikeUnlikeDto {

	private int postId;
	private int userId;
	private int count;
	
	public LikeUnlikeDto(int postId, int userId, int count) {
		this.postId = postId;
		this.userId = userId;
		this.count = count;
	}
	
	public LikeUnlikeDto(int postId, int userId) {
		this.postId = postId;
		this.userId = userId;
	}
	
	public LikeUnlikeDto(int postId) {
		this.postId = postId;
	}

	@Override
	public String toString() {
		return "LikeUnlikeDto [postId=" + postId + ", userId=" + userId + ", count=" + count + "]";
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	public int getPostId() {
		return postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
