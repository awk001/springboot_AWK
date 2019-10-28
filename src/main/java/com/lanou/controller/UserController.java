package com.lanou.controller;

import com.lanou.entity.User;
import com.lanou.msg.PhoneCode;
import com.lanou.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  /**
   * 根据前端发送过来的手机号码获取验证码
   * @return	返回的验证码
   */
  @GetMapping("/getValidate")
  public String getValidate(HttpServletRequest request){
    String phone = request.getParameter("tel");
    String phonemsg = PhoneCode.getPhonemsg(phone);
    System.out.println(phonemsg);
    return phonemsg;
  }

  /**
   * 检查账号是否重复
   * @param user
   * @return
   */
  @PostMapping("/checkName")
  public boolean checkName(@RequestBody User user){
//    System.out.println("测试checkName");
    String username = user.getUsername();
    if(username!=null) {
      User user2 = userService.findUserByUsername(username);
      System.err.println("user:"+user2);
      if(user2!=null)
        return true;
    }
    return false;
  }

  /**
   * 注册模块
   * @param user
   * @return
   */
  @PostMapping("/regist")
  public boolean regist(@RequestBody User user) {
    String password = user.getPassword();
    String username = user.getUsername();
    String userphone = user.getUserphone();
    System.out.println("用户名：" + username);
    System.out.println("密码：" + password);
    System.out.println("手机号：" + userphone);
    System.out.println(user);
    if (username == null || password == null || userphone == null) {
      return false;
    } else {
      User userSeg = new User();
      //md5加密
      String salt = new SecureRandomNumberGenerator().nextBytes().toString();
      int times = 2;
      String algorithmName = "md5";
      String encodePwd = new SimpleHash(algorithmName, password, salt, times).toString();
      userSeg.setUsername(username);
      userSeg.setPassword(encodePwd);
      userSeg.setUserphone(userphone);
      userSeg.setSalt(salt);
      userService.addUser(userSeg);
      return true;
    }
  }


  /**
   * 登录模块
   * @param user
   * @return
   */
  @PostMapping("/checkLogin")
  public boolean checkLogin(@RequestBody User user){
    String username=user.getUsername();
    String password = user.getPassword();
    if(username==null||password==null){
      return false;
    }else{
      //先通过username查找到当前对象
      User findUserByUsername = userService.findUserByUsername(user.getUsername());
      if(findUserByUsername==null){
        return false;
      }
      //获取当前对象的盐
      String saltValue = findUserByUsername.getSalt();
      //获取当前对象的密码
      String pwd = findUserByUsername.getPassword();
      int times = 2;
      String algorithmName="md5";
      //加密前端用户输入的明文密码
      String encodePwd = new SimpleHash(algorithmName,password,saltValue,times).toString();
      if(encodePwd.equals(pwd)){
        //将密文密码放到user中
        user.setPassword(encodePwd);
        //查询的密码是MD5加密后的密码
        User user2 = userService.findUserByNameAndPwd(user);
        return user2==null?false:true;
      }
      return false;
    }
  }
  @PostMapping("/checkUpdate")
  public boolean checkUpdateName(HttpServletRequest request){
    //获取前端页面的输入内容
    String username = request.getParameter("username");
    String pwd = request.getParameter("user_pwd");
    //根据用户名查询对象，查询当前对象是否存在
    User findUserByUsername = userService.findUserByUsername(username);
    System.out.println(findUserByUsername);
    if(findUserByUsername==null){
      return false;
    }
    //md5加密
    String saltVal = new SecureRandomNumberGenerator().nextBytes().toString();
    int times = 2;
    String algorithmName="md5";
    String encodePwd = new SimpleHash(algorithmName,pwd,saltVal,times).toString();
    findUserByUsername.setPassword(encodePwd);
    //修改密码
    userService.updateUserPwd(findUserByUsername);
    return true;
  }






}
