package org.lwd.microservice.boot.plat.controller;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author weidong
 * @version V1.0.0
 * @since 2023/6/25
 */
public class TestBao {
   /* @Test
    public void testCult() {
        BigDecimal amount = new BigDecimal("30000");
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 1; i <= 10; i++) {
            //sum = amount.add(sum).multiply(BigDecimal.valueOf(1.025)).setScale(2, RoundingMode.HALF_UP);
            sum = amount.add(sum).add(BigDecimal.valueOf(i).multiply(BigDecimal.valueOf(830))).setScale(2, RoundingMode.HALF_UP);
            System.out.println("前10年：" + sum.toString());
        }

        //后30年
        BigDecimal afterSum = sum;
        for (int i = 1; i <= 25; i++) {
            afterSum = afterSum.multiply(BigDecimal.valueOf(1.035)).setScale(2, RoundingMode.HALF_UP);
        }
        System.out.println("后30年：" + afterSum.toString());

        //保险公司给
        BigDecimal baoSum = new BigDecimal("300000");;
        for (int i = 1; i <= 35; i++) {
            baoSum = baoSum.add(BigDecimal.valueOf(7000)).setScale(2, RoundingMode.HALF_UP);
        }
        System.out.println("保险公司30年：" + baoSum.toString());
    }*/
}
