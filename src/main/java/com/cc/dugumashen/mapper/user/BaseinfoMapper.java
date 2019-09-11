package com.cc.dugumashen.mapper.user;

import com.cc.dugumashen.model.user.Baseinfo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface BaseinfoMapper {


    int deleteByPrimaryKey(String id);


    int insert(Baseinfo record);


    Baseinfo selectByPrimaryKey(String id);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Baseinfo record);

    List<Baseinfo> selectAllUserBaseInfo();
}