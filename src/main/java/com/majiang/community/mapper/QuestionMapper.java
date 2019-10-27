package com.majiang.community.mapper;

import com.majiang.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName QuestionMapper
 * @Description TODO
 * @Author 刘志强
 * @Date 2019/9/14 1:40
 * @Version v1.0
 **/
@Mapper
public interface QuestionMapper {

    @Insert("INSERT INTO question (title, description, gmt_create, gmt_modified, creator, tag) VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    public void create(Question question);

    @Select("SELECT * FROM question limit #{offset}, #{size}")
    List<Question> list(@Param("offset")Integer offset, @Param("size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();
}
