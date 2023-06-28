package org.lwd.microservice.boot.middle.log.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 日志操作类型
 *
 * @author lwd
 * @date 2023/6/10
 */
@Getter
@AllArgsConstructor
public enum LogTypeEnum {

    /**
     * 默认
     */
    DEFAULT(-1, "默认"),
    /**
     * 查询
     */
    SELECT(0, "查询"),
    /**
     * 添加
     */
    INSERT(1, "添加"),
    /**
     * 编辑
     */
    UPDATE(2, "编辑"),
    /**
     * 删除
     */
    DELETE(3, "删除"),
    /**
     * 导入
     */
    IMPORT(4, "导入"),
    /**
     * 导出
     */
    EXPORT(5, "导出"),

    /**
     * 上传
     */
    UPLOAD(6, "上传"),
    /**
     * 下载
     */
    DOWNLOAD(7, "下载"),

    /**
     * 同步
     */
    SYNC(8, "同步"),


    /**
     * 发送消息
     */
    SENDMSG(9, "发送消息"),

    /**
     * 其他
     */
    OTHER(10, "其他"),
    ;


    /**
     * 类型
     */
    private final Integer type;
    /**
     * 操作项
     */
    private final String name;
}
