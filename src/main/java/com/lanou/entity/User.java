package com.lanou.entity;

import java.io.Serializable;
@SuppressWarnings("all")
public class User implements Serializable{
  private int id;
  private String username;
  private String password;
  private String userphone;
  private String salt;

  public User() {
  }

  public User(int id, String username, String password, String userphone, String salt) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.userphone = userphone;
    this.salt = salt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserphone() {
    return userphone;
  }

  public void setUserphone(String userphone) {
    this.userphone = userphone;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", userphone='" + userphone +'\''+ "}";
  }
}
