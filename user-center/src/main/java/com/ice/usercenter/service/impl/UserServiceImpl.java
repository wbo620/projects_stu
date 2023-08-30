package com.ice.usercenter.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.usercenter.mapper.UserMapper;
import com.ice.usercenter.modle.domain.User;
import com.ice.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static com.ice.usercenter.contant.UserConstant.USER_LOGIN_STATE;


/**
 * @author hallen
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-08-28 21:19:04
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
  implements UserService {

  private static final String SALF = "wu bo";

  @Resource
  private UserMapper mapper;

  @Override
  public long userRegister(String userAccount, String userPassword, String checkPassword) {
    //1.校验
    //非空校验
    if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
      return -1;
    }
    //长度校验
    if (userAccount.length() < 4) {
      return -1;
    }
    if (userPassword.length() < 8 || checkPassword.length() < 8) {
      return -1;
    }
    //非法字符校验
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
    if (matcher.find()) {
      return -1;
    }
    //校验两次输入的密码
    if (!userPassword.equals(checkPassword)) {
      return -1;
    }
    //用户名不能重复(检验数据库)
    //账户不能重复
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    long count = this.count(queryWrapper);
    if (count > 0) {
      return -1;
    }
    //2. 加密
    String encryptPassword = DigestUtils.md5DigestAsHex((SALF + userPassword).getBytes());

    //3. 插入数据
    User user = new User();
    user.setUserAccount(userAccount);
    user.setUserPassword(encryptPassword);
    int saveResult = mapper.insert(user);

    //boolean saveResult = this.save(user);
    //
    if (saveResult != 1) {
      return -1;
    }
    return user.getId();
  }

  @Override
  public User login(String userAccount, String userPassword, HttpServletRequest request) {
    //1.校验
    //非空校验
    if (StringUtils.isAllBlank(userAccount, userPassword)) {
      return null;
    }
    //长度校验
    if (userAccount.length() < 4) {
      return null;
    }
    if (userPassword.length() < 8) {
      return null;
    }
    //非法字符校验
    String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
    if (matcher.find()) {
      return null;
    }

    //2. 加密
    String encryptPassword = DigestUtils.md5DigestAsHex((SALF + userPassword).getBytes());

    //查询用户是否存在
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("userAccount", userAccount);
    queryWrapper.eq("userPassword", encryptPassword);
    User user = mapper.selectOne(queryWrapper);
    //用户不存在
    if (user == null) {
      log.info("User not found,Please Check your username and password");
      return null;
    }


    //3.用户脱敏
    User safetyUser = getSafetyUser(user);

    //4.保存用户状态信息
    request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

    return safetyUser;
  }

  /**
   * 用户脱敏
   *
   * @param originUser
   * @return
   */
  @Override
  public User getSafetyUser(User originUser) {
    User safetyUser = new User();
    safetyUser.setId(originUser.getId());
    safetyUser.setUsername(originUser.getUsername());
    safetyUser.setUserAccount(originUser.getUserAccount());
    safetyUser.setAvatarUrl(originUser.getAvatarUrl());
    safetyUser.setGender(originUser.getGender());
    safetyUser.setPhone(originUser.getPhone());
    safetyUser.setEmail(originUser.getEmail());
    safetyUser.setPlanetCode(originUser.getPlanetCode());
    safetyUser.setUserRole(originUser.getUserRole());
    safetyUser.setUserStatus(originUser.getUserStatus());
    safetyUser.setCreateTime(originUser.getCreateTime());
    return safetyUser;
  }
}




