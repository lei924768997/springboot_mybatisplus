package com.mybatisplus.controller;
import com.mybatisplus.entity.CrimeInfo;
import com.mybatisplus.service.CrimeInfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Author: leiming
 * @Description:
 * @Date: 2023/4/7 18:57
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/api")
public class CrimeInfoController {

    @Autowired
    private CrimeInfoService crimeInfoService;


    @RequestMapping(value="/crime/stat",method = RequestMethod.GET)
    public List selectCrimeInfo(@RequestParam(value="token",required= true) String token){

        List<CrimeInfo> list = new ArrayList();
        CrimeInfo crimeInfo = new CrimeInfo();
        crimeInfo.setIncidentId("201271832");
        CrimeInfo c = crimeInfoService.selectById(crimeInfo);
        list.add(c);
        return  list;
    }


    @RequestMapping(value="/crime",method = RequestMethod.POST)
    public Map<String,Object> insertCrimeInfo(@RequestBody String str, @RequestParam(value="token",required= true) String token){


        Map<String,Object> result = new HashMap();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject = jsonObject.fromObject(str);
            CrimeInfo c  = com.alibaba.fastjson.JSONObject.parseObject(jsonObject.getString("data"),CrimeInfo.class);
            c.setCrimeName1("-");
            c.setCrimeName2("-");
            c.setCrimeName3("-");
            String id = crimeInfoService.selectID();
            c.setIncidentId(id);
            c.setId(Long.valueOf(id));
            boolean flag  = crimeInfoService.insertCrime(c);
            if(flag==true){
                Map<String,String> map = new HashMap();
                map.put("incidentId",id);
                result.put("code","0");
                result.put("message","ok");
                result.put("result",map);
            }
        }catch(Exception  e){
            e.printStackTrace();
            result.put("code","1");
            result.put("message",e.toString());
            result.put("result",null);
        }
        return  result;
    }

}
