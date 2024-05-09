package com.jou.demo.DAO;

import com.jou.demo.DO.QuestionDO;
import com.jou.demo.DO.ResponseDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionDAO {
    @Select("SELECT questionID,surveyID,content,type,order0 FROM questions")
    List<QuestionDAO> FindAll();

    @Insert("INSERT INTO questions (questionID,surveyID,content,type,order0) VALUES(#{questionID}, #{surveyID}, #{content}, #{type}, #{order})")
    int insert(QuestionDO questionDO);

    @Update("update questions set surveyID=#{surveyID},content=#{content},type=#{type} ,order0=#{order} where questionID=#{questionID}")
    int update(QuestionDO questionDO);
}
