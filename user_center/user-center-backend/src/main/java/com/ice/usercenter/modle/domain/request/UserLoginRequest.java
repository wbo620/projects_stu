package com.ice.usercenter.modle.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * User: hallen
 * Date: 2023/8/29
 * Time: 21:38
 */
@Data
public class UserLoginRequest implements Serializable {
  private static final long serialVersionUID = 21412948140141L;
  private String userAccount;
  private String userPassword;

}
