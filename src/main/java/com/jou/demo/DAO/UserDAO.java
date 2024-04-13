package com.jou.demo.DAO;

import com.jou.demo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserDAO {
    @Select("SELECT userID,name as userName,sex,avatar,password,submit FROM user")
    List<UserDO> findAll();

    @Insert("INSERT INTO user (userID, name, sex,avatar,password,submit) VALUES(#{userID}, #{name}, #{sex}, #{avatar},#{password},#{submit})")
    int insert(UserDO userDO);

    @Update("update user set name=#{name},avater=#{avatar},password=#{password} where userID=#{userID}")
    int update(UserDO userDO);
}
