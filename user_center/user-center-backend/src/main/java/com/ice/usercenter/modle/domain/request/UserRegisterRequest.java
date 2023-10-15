package com.ice.usercenter.modle.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User: hallen
 * Date: 2023/8/29
 * Time: 21:38
 */
@Data
public class UserRegisterRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private String userAccount;
  private String userPassword;
  private String checkPassword;
  private String planetCode;

  //接收默认昵称和头像
  private String username;
  private String avatarUrl;

}
