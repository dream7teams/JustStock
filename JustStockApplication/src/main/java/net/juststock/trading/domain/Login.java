package net.juststock.trading.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loginId;

	public long getLoginId() {
		return loginId;
	}

	public void setLoginId(long loginId) {
		this.loginId = loginId;
	}

	private long contact;

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public Login(long loginId, long contact) {
		super();
		this.loginId = loginId;
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", contact=" + contact + "]";
	}

}
