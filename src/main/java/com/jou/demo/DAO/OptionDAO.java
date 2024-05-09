package com.jou.demo.DAO;

import com.jou.demo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OptionDAO {
    @Select("SELECT optionID,questionID,content,order0 FROM option")
    List<UserDO> findAll();

    @Insert("INSERT INTO option (optionID,questionID,content,order0) VALUES(#{optionID}, #{questionID}, #{content}, #{order})")
    int insert(UserDO userDO);

    @Update("update option set questionID=#{questionID},content=#{content},order0=#{order} where optionID=#{optionID}")
    int update(UserDO userDO);
}
