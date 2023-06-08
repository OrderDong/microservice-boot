package org.lwd.microservice.boot.common.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

/**
 * @author weidong
 * @version V1.0.0
 * @description
 * @since 2023/4/7
 */

@Getter
@Setter
@Slf4j
public class User implements Serializable {

    private int id;
    private String name;
    private Date createTime;

    @Test
    public void test(){
        log.info("1111");
    }

}
