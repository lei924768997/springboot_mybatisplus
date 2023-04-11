package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.CrimeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName
 * @Author: leiming
 * @Description:
 * @Date: 2023/4/9 14:13
 * @Version 1.0.0
 */
@Repository
@Mapper
public interface CrimeInfoMapper  extends  BaseMapper<CrimeInfo>{

    @Select("select * from exam_1449.test1_data")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "incidentId", column = "incident_id"),
            @Result(property = "offenceCode", column = "offence_code"),
            @Result(property = "dispatchTime", column = "dispatch_time"),
            @Result(property = "victims", column = "victims"),
            @Result(property = "crimeName1", column = "crime_name1"),
            @Result(property = "crimeName2", column = "crime_name2"),
            @Result(property = "crimeName3", column = "crime_name3"),
            @Result(property = "city", column = "city"),
            @Result(property = "startDateTime", column = "start_date_time")
    })
    List<CrimeInfo> queryList();
}
