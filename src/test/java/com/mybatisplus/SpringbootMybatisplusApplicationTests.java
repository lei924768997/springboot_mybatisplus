package com.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisplus.entity.CrimeInfo;
import com.mybatisplus.mapper.CrimeInfoMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    private CrimeInfoMapper crimeInfoMapper;
    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;

    /**
     * 基本的单表查询功能
     */
    @Test
    void contextLoads() {

        //List<CrimeInfo> crimeList = crimeInfoMapper.queryList();
        // List<CrimeInfo> crimeList = crimeInfoMapper.selectList(null);
        //int a = crimeInfoMapper.deleteById(c);
        QueryWrapper<CrimeInfo> query = new QueryWrapper<>();
         query.eq("city","GERMANTOWN").eq("crime_name1","Other");
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(query);
        crimeList.forEach(System.out::println);
    }

    /**
     * 查询分页功能
     */
    @Test
    public void getInterceptor(){

        IPage page = new Page(1,10);
        crimeInfoMapper.selectPage(page,null);
        System.out.println("当前页："+page.getCurrent());
        System.out.println("每页显示数量："+page.getSize());
        System.out.println("总页数："+page.getPages());
        System.out.println("总记录数："+page.getTotal());
        System.out.println("数据："+page.getRecords());
    }

    /**
     * 按照条件查询
     */
    @Test
    public  void getContiaonQuery(){

        /*QueryWrapper qw = new QueryWrapper<CrimeInfo>();
        qw.eq("incident_id","201271825");
        qw.or(true);
        qw.gt("id","95");
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(qw);
        System.out.println(crimeList);*/

       /* QueryWrapper<CrimeInfo> qw = new QueryWrapper<CrimeInfo>();
        qw.lambda().eq(CrimeInfo::getCity,"GERMANTOWN");
        qw.lambda().or(true);
        qw.lambda().gt(CrimeInfo::getId,"95");
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(qw);
        System.out.println(crimeList);*/

        /*LambdaQueryWrapper<CrimeInfo> lqw = new LambdaQueryWrapper<CrimeInfo>();
        lqw.and(i -> i.eq(CrimeInfo::getId, "5").ne(CrimeInfo::getId, "7"));
        lqw.lt(CrimeInfo::getId,"12").or().gt(CrimeInfo::getId,"95");
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(lqw);
        System.out.println(crimeList);*/


        //条件查询，判断是否是null的情况，null不带入条件查询
       // LambdaQueryWrapper<CrimeInfo> lqw = new LambdaQueryWrapper<CrimeInfo>();
        //CrimeInfoQuery c = new CrimeInfoQuery();
        //c.setIncidentId1("201271826");
        //c.setIncidentId("201271890");
        //lqw.gt(null!=c.getIncidentId1(),CrimeInfo::getIncidentId,c.getIncidentId1())
         //       .lt(null !=c.getIncidentId(),CrimeInfo::getIncidentId,c.getIncidentId());
        /*c.setStartDateTime1("2020-01-01 15:34:00");
        c.setStartDateTime("2020-01-01 22:10:00");
        lqw.ge(null !=c.getStartDateTime1(),CrimeInfo::getStartDateTime,c.getStartDateTime1())
            .le(null != c.getStartDateTime(),CrimeInfo::getStartDateTime,c.getStartDateTime());
        List<CrimeInfo> crimeList =crimeInfoMapper.selectList(lqw);
        System.out.println(crimeList);*/

        //条件查询，查询多条in的方式
        QueryWrapper<CrimeInfo> q =new QueryWrapper<CrimeInfo>();
        q.select("city","crime_name1","crime_name2");
        q.eq("city","ROCKVILLE");
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(q);
        ArrayList<String> arr = new ArrayList<>();
        for(CrimeInfo c : crimeList){
            arr.add(c.getCity());
        }
        q.in("city",arr);
        List<CrimeInfo> list = crimeInfoMapper.selectList(q);
        System.out.println(list);

        /*LambdaQueryWrapper<CrimeInfo> qw = new LambdaQueryWrapper<CrimeInfo>();
        CrimeInfoQuery c = new CrimeInfoQuery();

        qw.select(CrimeInfo::getCity,CrimeInfo::getCrimeName1,CrimeInfo::getCrimeName2);
        qw.in(null!=c.getIncidentId1(),arr);
                .
        List<CrimeInfo> crimeList = crimeInfoMapper.selectList(qw);
        System.out.println(crimeList);*/
    }

    /**
     * 删除、更新、插入的操作
     */
    @Test
    public void deleteAndUpdate(){
        //单独删除
        //crimeInfoMapper.deleteById(5L);
        //批量删除
        /*List<Long> list = new ArrayList<Long>();
        list.add(7L);
        list.add(8L);
        list.add(9L);
        crimeInfoMapper.deleteBatchIds(list);*/

        //in的方式查询数据
       /* QueryWrapper<CrimeInfo> qw = new QueryWrapper<CrimeInfo>();
        qw.in("incident_id","201323681","201271826");
        crimeInfoMapper.selectMaps(qw);*/

       //逻辑删除操作
        //crimeInfoMapper.deleteById(14L);
        //crimeInfoMapper.selectById(12L);

        //乐观锁version   当在进行更新操作时，使用乐观锁的方式，可以顺利的对数据进行更新，避免出现更新失败的情况
        CrimeInfo c =  crimeInfoMapper.selectById(30L);
        c.setCity("常州");
        crimeInfoMapper.updateById(c);

        CrimeInfo c2 =  crimeInfoMapper.selectById(30L);
        c2.setCity("南京");
        crimeInfoMapper.updateById(c2);
    }

    /**
     * redis学习   redisTemplate
     */
    @Test
    public void redisTest(){
        //set方法
        //redisTemplate.opsForValue().set("name","lisi");
        //delete方法
        //redisTemplate.delete("name");

        LambdaQueryWrapper<CrimeInfo> qw = new  LambdaQueryWrapper<CrimeInfo>();
        qw.eq(CrimeInfo::getCity,"ROCKVILLE");
        List<CrimeInfo> list =  crimeInfoMapper.selectList(qw);
        redisTemplate.opsForValue().set("leiming:CrimeInfoList",list);
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * redis学习   stringRedisTemplate
     */
    @Test
    public void redisStringSerializer(){
        try {
            LambdaQueryWrapper<CrimeInfo> qw = new  LambdaQueryWrapper<CrimeInfo>();
            qw.eq(CrimeInfo::getIncidentId,"201271826");
            CrimeInfo c =  crimeInfoMapper.selectOne(qw);
            String json  = mapper.writeValueAsString(c);
            stringRedisTemplate.opsForValue().set("leiming:CrimeInfo",json);
            String a = stringRedisTemplate.opsForValue().get("leiming:CrimeInfo");
            System.out.println(a);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMybatis(){

        List<CrimeInfo> list =   crimeInfoMapper.queryListByIncidentId("201271826");
        list.stream().forEach(l->{

               System.out.println(l);

        });
    }

    @Test
    public void test11(){

        CrimeInfo crimeInfo = new CrimeInfo();
        CrimeInfo c = new CrimeInfo();
        try {
            BeanUtils.copyProperties(c,crimeInfo);  //对象之前的属性拷贝  将c拷贝到crimeInfo
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
