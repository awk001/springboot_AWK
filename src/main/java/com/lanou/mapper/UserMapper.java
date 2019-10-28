package com.lanou.mapper;

import com.lanou.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@SuppressWarnings("all")
public interface UserMapper {
  //查询所有用户
  @Select("select * from user")
  public List<User> findAllUsers();

  //根据id删除用户
  @Delete("delete from user where id=#{id}")
  public void deleteUserById(@Param("id") int id);

  //添加用户
  @Insert("insert into user(username,password,userphone,salt) values(#{username},#{password},#{userphone},#{salt})")
  public void addUser(User user);

  //根据用户名查询对象
  @Select("select * from user where username=#{username}")
  public User findUserByName(@Param("username") String username);

  //根据用户名密码查询对象
  @Select("select * from user where username=#{username} and password=#{password}")
  public User findUserByNameAndPwd(@Param("username")String username,@Param("password")String password);

  //忘记密码
  @Update("update user set password = #{password}")
  public void updateUser(@Param("password")String password);
}
