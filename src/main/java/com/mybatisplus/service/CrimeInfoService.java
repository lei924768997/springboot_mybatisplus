package com.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mybatisplus.config.CommonError;
import com.mybatisplus.config.LeiMException;
import com.mybatisplus.entity.CrimeInfo;
import com.mybatisplus.mapper.CrimeInfoMapper;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName
 * @Author: leiming
 * @Description:
 * @Date: 2023/4/7 18:57
 * @Version 1.0.0
 */
@Service
public class CrimeInfoService {

    @Autowired
    private CrimeInfoMapper crimeInfoMapper;


    /**
     * 插入
     * @param c
     * @return
     */
    public boolean insertCrime(CrimeInfo c){

      return crimeInfoMapper.insetCrimeInfo(c);
    }

    /**
     * 查询一个
     * @param c
     * @return
     */
    public CrimeInfo selectById(CrimeInfo c){

        if(StringUtils.isBlank(c.getIncidentId())){

            //1、throw new RuntimeException("不能为空");
            //2、LeiMException.cast("不能为空");       使用自定义异常
            //3、LeiMException.cast(CommonError.OBJECT_NULL);  使用自定义异常
        }

        LambdaQueryWrapper<CrimeInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CrimeInfo::getIncidentId,c.getIncidentId());
        return crimeInfoMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 查询id
     * @return
     */
    public String selectID(){

        return crimeInfoMapper.selectID();
    }
}
