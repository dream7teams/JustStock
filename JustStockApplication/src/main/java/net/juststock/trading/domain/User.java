package net.juststock.trading.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 40)
  private long contact; // phone/email

  @Column(nullable = false, length = 120)
  private String fullName;

  // getters/setters
  public Long getId() { return id; }
  public long getContact() { return contact; }
  public void setContact(long contact) { this.contact = contact; }
  public String getFullName() { return fullName; }
  public void setFullName(String fullName) { this.fullName = fullName; }
}
