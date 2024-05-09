package com.jou.demo.DAO;

import com.jou.demo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDAO {
    @Select("SELECT userID,name as userName,sex,password,submit FROM user where userID=#{userID}")
    UserDO findUserByID(String userID);


    @Insert("INSERT INTO user (userID, name, sex,password,submit) VALUES(#{userID}, #{name}, #{sex}, #{password},#{submit})")
    int insert(UserDO userDO);

    @Update("update user set name=#{name},password=#{password} where userID=#{userID}")
    int update(UserDO userDO);
}
