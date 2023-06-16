package org.lwd.microservice.boot.generation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.lwd.microservice.boot.generation.engine.EnhanceFreemarkerTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.INTEGER;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/5/26
 */
public class CodeGeneration {
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir") + "/microservice-boot-module/microservice-boot-common"; //获取项目路径
        String outerFilePath = projectPath + "/src/main/java";  //java下的文件路径
        String packageName = "org.lwd.microservice.boot.common";
        String tableName = "t_tenant_data_source";

        System.out.println(projectPath);
        FastAutoGenerator.create(
                new DataSourceConfig.Builder("jdbc:mysql://" + "127.0.0.1:3306/test", "root", "123456")
                        .dbQuery(new MySqlQuery())
                        .typeConvert(new MySqlTypeConvert() {
                            @Override
                            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                                if (fieldType.toLowerCase().contains("tinyint")) {
                                    return INTEGER;
                                }
                                return super.processTypeConvert(config, fieldType);
                            }
                        })
                        .keyWordsHandler(new MySqlKeyWordsHandler()))
                //全局配置
                .globalConfig(builder -> {
                    builder.outputDir(outerFilePath)//生成的输出路径
                            .author("lwd")//生成的作者名字
                            //.enableSwagger()开启swagger，需要添加swagger依赖并配置
                            .dateType(DateType.ONLY_DATE)//时间策略
                            .commentDate("yyyy-MM-dd")//格式化时间格式
                            .disableOpenDir();//禁止打开输出目录，默认false
                })
                //包配置
                .packageConfig(builder -> {
                    builder.entity("entity")//实体类包名
                            .parent(packageName)//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                            .controller("controller")//控制层包名
                            .mapper("dao")//mapper层包名
                            .xml("mapper.xml")//数据访问层xml包名
                            .service("service")//service层包名
                            .serviceImpl("service.impl")//service实现类包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper")); //路径配置信息,就是配置各个文件模板的路径信息,这里以mapper.xml为例
                })
                //模版配置
                .templateConfig(builder -> {
//                    builder.disable()//禁用所有模板
//                    builder.disable(TemplateType.ENTITY)
                    builder.service("templates/service.java")//service模板路径
                            .serviceImpl("templates/serviceImpl.java")//实现类模板路径
//                .mapper("templates/mapper.java")//mapper模板路径
//                .mapperXml("/templates/mapper.xml")//xml文件模板路路径
                            .controller("templates/controller.java"); //controller层模板路径
                }).templateEngine(new FreemarkerTemplateEngine())
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO 下面的key会作为类名后缀，进而生成新类
                    customFile.put("DTO.java", "templates/other/dto.java.ftl");
                    customFile.put("VO.java", "templates/other/vo.java.ftl");
                    customFile.put("Convertor.java", "templates/other/convertor.java.ftl");
                    consumer.customFile(customFile);
                }).templateEngine(new EnhanceFreemarkerTemplateEngine())
                // EnhanceFreemarkerTemplateEngine 里主要重写对自定义类的处理 如vo dto convert等
                //策略配置开始
                .strategyConfig(builder -> {
                    builder.enableCapitalMode()//开启全局大写命名
                            //.likeTable()模糊表匹配
                            .addInclude(tableName)//添加表匹配，指定要生成的数据表名，不写默认选定数据库所有表
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
                            .logicDeleteColumnName("deleted_tag")//逻辑删除标识

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

    }

}
