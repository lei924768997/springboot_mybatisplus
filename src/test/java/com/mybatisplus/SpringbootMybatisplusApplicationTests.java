package com.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisplus.entity.CrimeInfo;
import com.mybatisplus.entity.Student;
import com.mybatisplus.exception.MyException;
import com.mybatisplus.mapper.CrimeInfoMapper;
import com.mybatisplus.mapper.SysUserMapper;
import com.mybatisplus.pojo.SysUser;
import org.apache.commons.beanutils.BeanUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.DigestUtils;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
class SpringbootMybatisplusApplicationTests {

    @Autowired
    private CrimeInfoMapper crimeInfoMapper;
    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;
    
    @Autowired
    private SysUserMapper sysUserMapper;

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

    /**
     * 文件的读取和写入  读取一个文件，将文件写成多个块
     */
   @Test
    public void  readAndWrite() throws IOException{
       //需要读取文件的路径
       File readFile = new File("E:\\admin\\ttt.xlsx");
       //需要写入文件的路径
        File writeFile = new File("E:\\admin\\test");
        if(!writeFile.exists()){

            writeFile.mkdirs();
        }
        //创建分块的大小1M
        int writeSize = 1024*1024*1;
        //需要分成多少块
        long writeNum =(long) Math.ceil(readFile.length()*1.0/writeSize);
        //建立缓存区
        byte[] num = new byte[1024];
        //读取文件
        RandomAccessFile read = new RandomAccessFile(readFile,"r");
        for(long i = 0;i < writeNum;i++){
            //把文件分成多个块
            File file = new File("E:\\admin\\test\\" +i);
            if(file.exists()){
                file.delete();
            }
            boolean newfile = file.createNewFile();
            if(newfile){
                RandomAccessFile write = new RandomAccessFile(file,"rw");
                int len = -1;
                while((len = read.read(num)) != -1){
                    write.write(num,0,len);
                    if(file.length()>= writeSize){
                        break;
                    }
                }
                write.close();
            }
        }
       read.close();
     }

    /**
     * 将多个块的文件合并为一个文件
     */
    @Test
    public void WriteAndRead()throws  IOException{

        //源文件
        File readFile = new File("E:\\admin\\ttt.xlsx");
        //分块文件存储路径
        File chunkFolderPath = new File("E:\\admin\\test\\");
        if(!chunkFolderPath.exists()){

            chunkFolderPath.mkdirs();
        }
        //定义合并后的文件名
        File margeFlie = new File("E:\\admin\\ttt_1.xlsx");
        //将分块文件分别读取出来，合并文件
        File[] chunkFiles = chunkFolderPath.listFiles();
        List<File> chunkFileList = Arrays.asList(chunkFiles);
        Collections.sort(chunkFileList,new Comparator<File>(){

            @Override
            public int compare(File o1,File o2){

                return Integer.parseInt(o1.getName())-Integer.parseInt(o2.getName());
            }
        });
        byte[] b = new byte[1024];
        RandomAccessFile write = new RandomAccessFile(margeFlie,"rw");
        for(File file : chunkFileList){
            RandomAccessFile read = new RandomAccessFile(file,"r");
            int len = -1;
            while((len=read.read(b)) != -1){

                write.write(b,0,len);
            }
        }
        FileInputStream sourceFile = new FileInputStream(readFile);
        FileInputStream margeFile = new FileInputStream(margeFlie);
        String sourceMd5 =  DigestUtils.md5DigestAsHex(sourceFile);
        String margeMd5 =  DigestUtils.md5DigestAsHex(sourceFile);
        if(sourceMd5.equals(margeMd5)){

            System.out.println("合并成功    ");
        }
    }
        /*
         * stream流的处理
         **/
    @Test
    public void test2(){

        /*Stream<T> filter(Predicate<? super T> predicate)<R> Stream<R> map(Function<? super T, ? extends R> mapper);
        Stream<T> distinct();Stream<T> sorted();Stream<T> sorted(Comparator<? super T> comparator);
        Stream<T> peek(Consumer<? super T> action);
        Stream<T> limit(long maxSize);Stream<T> skip(long n);*/

        //获得前10个偶数
        //Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        //生成
        //Stream.generate(Math::random).limit(10).forEach(System.out::println);

        List<String> list = Arrays.asList("北京","北京","天津","东京","西京","普京");
        //System.out.println(list.stream().count());
        //找出集合中最大的
        //Collections.max(list);
        //找出集合中含有京的第一个元素
        //System.out.println(list.stream().filter(item->item.contains("京")).findFirst());
        //找出集合中含有京的集合
        //System.out.println(list.stream().filter(item -> item.contains("京")).collect(Collectors.toList()));
        //对集合中的元素去重
        //list.stream().distinct().collect(Collectors.toList());
        //截取元素，返回前2个元素
        //list.stream().limit(2).collect(Collectors.toList());
        //跳过n个元素
        //list.stream().skip(2).collect(Collectors.toList());
        List<Student> lists = getStudent();
        //排序 默认不写是升序
        //List<Student> collect = lists.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        //找出集合中密码中最大数字的对象
        //lists.stream().max(Comparator.comparing(Student::getPassword)).get();
        //List<String> str = new ArrayList<>();
        //将list中的数据放在map中，toMap指定map的key和value
        lists.stream().collect(Collectors.toMap(Student::getAge,Function.identity()));
        //将name取出，重新放在一个集合中
        //List<String> collect = lists.stream().map(Student::getUsername).collect(Collectors.toList());
        //List<String> str = lists.stream().map(item -> item.getUsername()).collect(Collectors.toList());
        //取出相同姓名的，放在集合中
        //List<String> names = lists.stream().filter(bean -> str.contains(bean.getUsername())).map(Student::getUsername).collect(Collectors.toList());

        List<String> newList = Arrays.asList("北 京 去","上 海 来","天 津 到","东 京 飞","西 京 想","普 京 牛 逼");
        /*Stream<Stream<String>> streamStream = newList.stream()
                                              .map(item -> item.split(" "))
                                              .map(Arrays::stream);*/


        //将上面的集合中的不相同的汉字列出来
        List<String> collect1 = newList.stream()//转为流对象
                .map(item -> item.split(" "))//将每个集合中的元素用空格分开，形成一个元素一个数组流Stream<Stream<String>>
                .flatMap(Arrays::stream)//空格区分后，形成新的集合，
                .distinct()//去重
                .collect(Collectors.toList());//转为list
        //lists.stream().flatMap(Student::getUsername).collect(Collectors.toList());

    }
        /**
         * @Author: leim
         * @Description    数组的api
         * @date: 2023/5/27
         **/
    @Test
    public void arraysTest(){

        int[] arr = {1,3,5,2,4};
        //数组排序
        /*Arrays.sort(arr);
        Arrays.stream(arr).forEach(item->System.out.println(item));*/
        //toString()
        System.out.println(Arrays.toString(arr));
        //copyOf  将原数组数据复制到新数组中
        int[] ints = Arrays.copyOf(arr, arr.length);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * @Author: leim
     * @Description      日期和时间的api
     * @date: 2023/5/27
     **/
    @Test
    public void dateTest(){

        //年-月-日
        LocalDate now = LocalDate.now();
        //时-分-秒-毫秒
        LocalTime.now();
        //年-月-日  时-分-秒-毫秒
        LocalDateTime now1 = LocalDateTime.now();
        //取年月日
        now1.toLocalDate();
        //取时分秒
        now1.toLocalTime();

        //格式化时间
        LocalDateTime now2 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(now2.format(format));

        //字符串时间转为localDateTime
        String s= "2023/05/27 10:20:43";
        LocalDateTime parse = LocalDateTime.parse(s, format);
        System.out.println(parse);
        //plus方法 对时间进行加减
        parse.plusDays(1);
    }

    /**
     * @Author: leim
     * @Description  自定义 运行时异常 throw:是主动抛异常    throws:是被动的抛异常
     * @date: 2023/5/27
     **/
    @Test
    public void exceptionTest() {
        try {
            String date = "2022/01/01";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parse = sdf.parse(date);
        } catch (Exception e) {

            throw  new MyException("不能为空");
        }
    }

    @Test
    public void listTest(){

        List<Object> list = new ArrayList<>();
        list.add(111);
        list.add("aaa");
        list.add("ccc");
        list.add(true);
        list.add(222);

        //迭代器遍历
        Iterator<Object> iterator = list.iterator();
        while(iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }
        //java1.8新特性，removeIf方法是按照条件删除集合的元素，需要重写接口Predicate的test的方法，
        //return t instanceof  Integer; 代表t的真实类型是Integer的话，返回就是true，代表删除集合中的Integer类型的元素
        //removeIf的底层是循环list中的元素，判断满足条件就删除
        list.removeIf(item->item instanceof Integer);//lambda表达式的写法
        //实现接口的写法
        list.removeIf(new Predicate<Object>(){
           public boolean test(Object t){

               return t instanceof  Integer;
            }
        });
        System.out.println(list);
    }


    @Test
    public void test3(){

        List<String> list = Arrays.asList("北京","南京","天津","东京","西京","普京");

        List<String> filterStrs = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterStrs);

        List<String> filterStrs1 = filterString(list,s -> s.contains("京"));
        System.out.println(filterStrs1);

    }


    //根据给定的规则，过滤集合中的字符串。此规则由Predicate的方法决定
    public List<String> filterString(List<String> list, Predicate<String> pre){

        ArrayList<String> filterList = new ArrayList<>();

        for(String s : list){
            if(pre.test(s)){
                filterList.add(s);
            }
        }

        return filterList;

    }

    public List<Student> getStudent(){

        List<Student> list = new ArrayList<>();
        //定义三个用户对象
        Student user1 = new Student();
        user1.setUsername("张三");
        user1.setPassword("123");
        user1.setAge(8);
        Student user2 = new Student();
        user2.setUsername("李四");
        user2.setPassword("3");
        user2.setAge(6);
        Student user3 = new Student();
        user3.setUsername("王五");
        user3.setPassword("2");
        user3.setAge(2);
        //添加用户到集合中
        list.add(user1);
        list.add(user2);
        list.add(user3);

        return list;
    }

    @Test
    public void testDemo(){

        List<String> newList = Arrays.asList("北 京 去","上 海 来","天 津 到","东 京 飞","西 京 想","普 京 牛 逼");

        List<Student> student = getStudent();
        /*student.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge()-o2.getAge();
            }
        });*/
         //student.sort(Comparator.comparing(Student::getAge));
        student.stream().sorted(Comparator.comparing(Student::getAge));

        student.stream().forEach(item->System.out.println(item));


    }

    @Test
    public void testDG(){
        System.out.println(getDG(5));
    }

    public int getDG(int num){
        if(num ==0){
            System.out.println("----");
            return 1;
        }
        int b = num * getDG(num - 1);
        System.out.println(num+"----" + b);
        return  b;
    }

    @Test
    public void testFile(){
        try{
            File file = new File("C:\\Users\\admin\\Desktop\\a");
            if(!file.exists()){
                file.mkdirs();
            }
            //getFile(file);//获取文件和子目录
            //deletFile(file);//删除文件和子目录
        }catch (Exception e){

            e.printStackTrace();
        }
    }
    /**
     * @Author: leim
     * @Description  获取文件夹下面的是.txt的文件，包含子目录下面的
     * @date: 2023/6/24
     **/
    public void getFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();//获取文件下的所有文件包括子目录
            for (File f : files) {
                // f.isDirectory()  判断是否是文件夹
                if (f.isFile()) {//判断是否是文件
                    if (f.getName().endsWith(".txt")) {
                        System.out.println(f.getName());
                    }
                } else {
                    getFile(f);
                }
            }
        }
    }
    /**
     * @Author: leim
     * @Description  删除指定文件夹下的所有文件和文件夹 包括本身
     * @date: 2023/6/24
     **/
    public void deletFile(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (File f: files) {
                 if(f.isFile()){
                     f.delete();
                 }else{
                     deletFile(f);
                 }
            }
            file.delete();
        }
    }


    /**
     * @Author: leim
     * @Description    相对于word和excel使用字符流读取和写入效率快些（其中包含汉字等）
     *                  由于视频和图片底层都是字节，所以只能使用字节流对它们进行操作
     *                  IO流
     *                     流向
     *                           输入流   读取数据    FileReader  父类：Reader
     *                           输出流   写出数据    FileWriter  父类：Writer
     *                      数据类型
     *                           字节流
     *                                  字节输入流  读取数据    InputStream
     *                                  字节输出流  写出数据    OutputStream
     *                           字符流
     *                                  字符输入流  读取数据   Reader
     *                                  字符输出流  写出数据   Writer
     * @date: 2023/6/24
     **/
    @Test
    public void testInputStream(){

        try{
             
            FileReader fr = new FileReader("C:\\Users\\admin\\Desktop\\redis.txt");
            FileWriter fw = new FileWriter("C:\\Users\\admin\\Desktop\\redisCopy.txt");
            //一次读一个字符，读取到的字符不等于-1，代表读取到了数据
            /*int len;
            while((len = fr.read()) != -1){
                fw.write(len);
                fw.flush();
            }*/

            //一次读一个长度是1024的字符数组,，读取到的字符不等于-1，代表读取到了数据
            int len;
            char[] a = new char[1024];
            while((len = fr.read(a)) != -1){
                fw.write(a,0,len);
                fw.flush();
            }

            fr.close();
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * @author leim
     * @date 2024/2/23
     * @description 注解的使用
     */
    @Test
    public void test0223() {
        SysUser sysUser = sysUserMapper.selectById("32041146735807370123");
        System.out.println(sysUser);
    }
}
