package com.ice.usercenter.service;

import com.ice.usercenter.modle.domain.User;

/**
 * User: hallen
 * Date: 2023/9/2
 * Time: 22:59
 */
public interface SelectService {
    User selectUserByName(String username);

    User selectUserByAccount(String userAccount);
    User selectUserByPhone(String phone);
    User selectUserByGender(String gender);
    User selectUserByEmail(String email);
    User selectUserByStatus(String status);

    User selectUserByUserRole(String userRole);
    User selectUserByPlanetCode(String planetCode);



}
