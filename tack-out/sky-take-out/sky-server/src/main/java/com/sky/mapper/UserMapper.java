package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.User;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * User: hallen
 * Date: 2023/9/9
 * Time: 15:49
 */
@Mapper
public interface UserMapper {
    @Select("select count(*) from user where openid=#{id}")
    int getUserById(String openid);

    @Insert("insert into user (openid, name, phone, sex, id_number, avatar, create_time)" +
            " VALUES(#{openid},#{name},#{phone},#{sex},#{idNumber},#{avatar},#{createTime}) ")
    void saveUser(User user);


    @Select("select id, openid, name, phone, sex, id_number, avatar, create_time from user where openid = #{openid}")
    User getByOpenid(String openid);
}
