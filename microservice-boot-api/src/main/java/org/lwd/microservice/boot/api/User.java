package org.lwd.microservice.boot.api;

import lombok.Getter;
import lombok.Setter;

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
public class User implements Serializable {

    private int id;
    private String name;
    private Date createTime;

}
