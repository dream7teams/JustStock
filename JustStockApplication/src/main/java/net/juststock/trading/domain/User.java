package net.juststock.trading.domain;

import jakarta.persistence.Entity;

@Entity
public class User {

	private long userId;
	private String userName;
	private String contact;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", contact=" + contact + "]";
	}

	public User(String userName, String contact) {
		super();
		this.userName = userName;
		this.contact = contact;
	}

	public User() {
		super();
	}
}
