package org.lwd.microservice.boot.middle.log.entity;

/**
 * 控制台日志类型
 *
 * @author lwd
 * @since 2023/06/20
 */
public enum LogConsoleTypeEnum {

    API("API", "接口"),

    SERVICE("Service", "服务"),

    DUBBO_SERVICE("dubboService", "dubbo服务"),

    ;

    private String name;
    private String desc;

    LogConsoleTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
