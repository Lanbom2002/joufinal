package com.jou.demo.DAO;

import com.jou.demo.DO.ResponseDO;
import com.jou.demo.DO.SurveyDO;
import com.jou.demo.DO.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ResponseDAO {
    @Select("SELECT responseID,surveyID,submittedTime FROM responses")
    List<ResponseDO> FindAll();

    @Insert("INSERT INTO survey (responseID,surveyID,submittedTime) VALUES(#{responseID}, #{surveyID}, now())")
    int insert(ResponseDO responseDO);

    @Update("update survey set surveysID=#{surveysID},titler=#{title},description=#{description} ,createdate=now() where surveysID=#{surveysID}")
    int update(ResponseDO responseDO);


}
