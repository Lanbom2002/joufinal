package com.jou.demo.DAO;

import com.jou.demo.DO.ResponseDO;
import com.jou.demo.DO.SurveyDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ResponseDetailDAO {
    @Select("SELECT responsID,surveyID,submittedTime FROM response")
    List<ResponseDO> FindAll();

    @Insert("INSERT INTO response (responsID,surveyID,submittedTime) VALUES(#{responsID}, #{surveyID},now())")
    int insert(ResponseDO responseDO);

    @Update("update response set responsID=#{responsID},surveyID=#{surveyID},submittedTime=now() where responsID=#{responsID}")
    int update(ResponseDO responseDO);
}
