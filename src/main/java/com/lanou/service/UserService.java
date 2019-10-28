package com.lanou.service;

import java.util.List;

import com.lanou.entity.User;

public interface UserService {

  public List<User> findAll();

  public void deleteUser(int id);

  public void addUser(User user);

  public User findUserByUsername(String name);

  public User findUserByNameAndPwd(User user);

  public void updateUserPwd(User user);

}
