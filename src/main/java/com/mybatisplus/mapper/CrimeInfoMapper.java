package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.CrimeInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface CrimeInfoMapper  extends  BaseMapper<CrimeInfo>{

    /*@Select("select * from exam_1449.test1_data")
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
    })*/
    //List<CrimeInfo> queryList();

    List<CrimeInfo> queryListByIncidentId(@RequestParam("incidentId") String incidentId);

    @Insert({"insert into test1_data(incident_id, offence_code,dispatch_time,victims,crime_name1,crime_name2,crime_name3,city,start_date_time) values(#{incidentId}, #{offenceCode},#{dispatchTime,jdbcType=TIMESTAMP},#{victims},#{crimeName1},#{crimeName2},#{crimeName3},#{city},#{startDateTime,jdbcType=TIMESTAMP})" })
    boolean insetCrimeInfo(CrimeInfo p);

    @Select("select max(id)+1 from test1_data")
    String selectID();

    /**
     * 多对多关系
     * @param id
     * @return
     */
    List<CrimeInfo> queryMapByID(@RequestParam("id") Long id);
}
