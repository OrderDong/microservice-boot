package org.lwd.microservice.boot.generation.engine;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * 对自定义的类处理，即other里生成的类
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {

        // 可以调用 tableInfo 的getFieldNames方法获得所有的列
        this.printTableColumn(tableInfo);

        // objectMap 里的key可以在ftl文件中直接引用
        String entityName = tableInfo.getEntityName();
        String entityPath = this.getPathInfo(OutputFile.entity);
        customFile.forEach((key, value) -> {
            // 拼接路径
            String fileName = String.format(entityPath + File.separator + entityName + "%s", key);
            if (key.equals("DTO.java")) {
                fileName = String.format(entityPath + File.separator + "dto" + File.separator + entityName + "%s", key);
            } else if (key.equals("VO.java")) {
                fileName = String.format(entityPath + File.separator + "vo" + File.separator + entityName + "%s", key);
            } else if (key.equals("Convertor.java")) {
                fileName = String.format(entityPath + File.separator + "convertor" + File.separator + entityName + "%s", key);
            }
            System.out.println(fileName);
            this.outputFile(new File(fileName), objectMap, value);
        });
    }

    /**
     * 获得所有的表列名
     *
     * @param tableInfo 表信息
     */
    private void printTableColumn(TableInfo tableInfo) {
        System.out.println("所有的列名：" + tableInfo.getFieldNames());
    }
}
