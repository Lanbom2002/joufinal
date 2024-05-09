package com.jou.demo.DAO;

import com.jou.demo.DO.SurveyDO;
import com.jou.demo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SurveyDAO {

    @Select("SELECT surveysID,title,description,createdate FROM survey")
    List<SurveyDO> FindAll();

    @Insert("INSERT INTO survey (surveysID,title,description,createdate) VALUES(#{surveysID}, #{title}, #{description}, now())")
    int insert(SurveyDO surveyDO);

    @Update("update survey set surveysID=#{surveysID},titler=#{title},description=#{description} ,createdate=now() where surveysID=#{surveysID}")
    int update(SurveyDO surveyDO);

}
