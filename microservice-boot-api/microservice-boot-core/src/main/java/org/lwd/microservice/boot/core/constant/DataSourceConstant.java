package org.lwd.microservice.boot.core.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源相关常量
 * @author lwd
 * @date 2023/6/6
 */
public class DataSourceConstant {

    public static final String TENANT_SOURCE_HEADER = "tenant_";
    public static List<String> YAML_DATASOURCE_LIST;

    static {
        YAML_DATASOURCE_LIST = new ArrayList<>(8);
        YAML_DATASOURCE_LIST.add("master");
        YAML_DATASOURCE_LIST.add("slave_1");
    }
}
