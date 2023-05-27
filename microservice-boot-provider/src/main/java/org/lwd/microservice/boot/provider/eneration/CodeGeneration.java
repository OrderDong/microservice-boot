package org.lwd.microservice.boot.provider.eneration;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/5/26
 */
public class CodeGeneration {
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir")+"/dubbo3-test-provider"; //获取项目路径
        String filePath = projectPath + "/src/main/java";  //java下的文件路径

        System.out.println(projectPath);
        /*
         * 模板配置
         */
//         如果模板引擎是 freemarker
//         String templatePath = "/templates/mapper.xml.ftl";
//         如果模板引擎是 velocity
//         String templatePath = "/templates/mapper.xml.vm";

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test","root","123456")
                //全局配置
                .globalConfig(builder -> {
                    builder.outputDir(filePath)//生成的输出路径
                            .author("lwd")//生成的作者名字
                            //.enableSwagger()开启swagger，需要添加swagger依赖并配置
                            .dateType(DateType.TIME_PACK)//时间策略
                            .commentDate("yyyy年MM月dd日")//格式化时间格式
                            .disableOpenDir();//禁止打开输出目录，默认false
                })
                //包配置
                .packageConfig(builder -> {
                    builder.entity("entity")//实体类包名
                            .parent("com.dubbo3.test.provider")//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .controller("controller")//控制层包名
                            .mapper("dao")//mapper层包名
                            .xml("mapper.xml")//数据访问层xml包名
                            .service("service")//service层包名
                            .serviceImpl("service.impl")//service实现类包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper")); //路径配置信息,就是配置各个文件模板的路径信息,这里以mapper.xml为例
                })
                //模版配置
                .templateConfig(builder -> {
//                    builder.disable()//禁用所有模板
//                .disable(TemplateType.ENTITY)禁用指定模板
//                .service(filePath + "/service.java")//service模板路径
//                .serviceImpl(filePath + "/service/impl/serviceImpl.java")//实现类模板路径
//                .mapper(filePath + "/mapper.java")//mapper模板路径
//                .mapperXml("/templates/mapper.xml")//xml文件模板路路径
//                .controller(filePath + "/controller")//controller层模板路径
                })
                //策略配置开始
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()//开启全局大写命名
                            //.likeTable()模糊表匹配
                            .addInclude()//添加表匹配，指定要生成的数据表名，不写默认选定数据库所有表
                            //.disableSqlFilter()禁用sql过滤:默认(不使用该方法）true
                            //.enableSchema()启用schema:默认false
                            .addTablePrefix("t_")//增加过滤表前缀
                            .entityBuilder() //实体策略配置
                            //.disableSerialVersionUID()禁用生成SerialVersionUID：默认true
                            .enableChainModel()//开启链式模型
                            .enableLombok()//开启lombok
                            .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //.addTableFills()添加表字段填充
                            .naming(NamingStrategy.underline_to_camel)//数据表映射实体命名策略：默认下划线转驼峰underline_to_camel
                            .columnNaming(NamingStrategy.underline_to_camel)//表字段映射实体属性命名规则：默认null，不指定按照naming执行
                            .idType(IdType.AUTO)//添加全局主键类型
                            .formatFileName("%s")//格式化实体名称，%s取消首字母I

                            .mapperBuilder()//mapper文件策略
                            .enableMapperAnnotation()//开启mapper注解
                            .enableBaseResultMap()//启用xml文件中的BaseResultMap 生成
                            .enableBaseColumnList()//启用xml文件中的BaseColumnList
                            //.cache(缓存类.class)设置缓存实现类
                            .formatMapperFileName("%sMapper")//格式化Dao类名称
                            .formatXmlFileName("%sMapper")//格式化xml文件名称

                            .serviceBuilder()//service文件策略
                            .formatServiceFileName("%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName("%sServiceImpl")//格式化 service 接口文件名称

                            .controllerBuilder()//控制层策略
                            //.enableHyphenStyle()开启驼峰转连字符，默认：false
                            .enableRestStyle()//开启生成@RestController
                            .formatFileName("%sController");//格式化文件名称
                }).execute();


        /**
         * 注入配置,自定义配置一个Map对象
         */
//    Map<String,Object> map = new HashMap<>();
//        map.put("name","young");
//        map.put("age","22");
//        map.put("sex","男");
//        map.put("description","深情不及黎治跃");
//
//    InjectionConfig injectionConfig = new InjectionConfig.Builder()
//            .customMap(map)
//            .build();
    }

}
