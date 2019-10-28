package com.lanou.service;

import com.lanou.entity.User;
import com.lanou.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper mapper;

  @Override
  public List<User> findAll() {
    return mapper.findAllUsers();
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED,
    isolation=Isolation.REPEATABLE_READ)
  public void deleteUser(int id) {
    mapper.deleteUserById(id);
  }

  @Override
  @Transactional(propagation= Propagation.REQUIRED,
    isolation= Isolation.REPEATABLE_READ)
  public void addUser(User user) {
    mapper.addUser(user);
  }

  @Override
  public User findUserByUsername(String name) {
    return mapper.findUserByName(name);
  }

  @Override
  public User findUserByNameAndPwd(User user) {
    return mapper.findUserByNameAndPwd(user.getUsername(),user.getPassword());
  }

  @Override
  @Transactional(propagation=Propagation.REQUIRED,
    isolation=Isolation.REPEATABLE_READ)
  public void updateUserPwd(User user) {
    mapper.updateUser(user.getPassword());
  }
}
