package net.juststock.trading.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users") 
public class User {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable=false, length=120)
  private String userName;

  @Column(nullable=false, length=20)
  private Long contact;

  public User() {}
  public User(String userName, Long contact){ this.userName=userName; this.contact=contact; }
  public Long getUserId() {
	return userId;
  }
  public void setUserId(Long userId) {
	this.userId = userId;
  }
  public String getUserName() {
	return userName;
  }
  public void setUserName(String userName) {
	this.userName = userName;
  }
  public Long getContact() {
	return contact;
  }
  public void setContact(Long contact) {
	this.contact = contact;
  }

}
