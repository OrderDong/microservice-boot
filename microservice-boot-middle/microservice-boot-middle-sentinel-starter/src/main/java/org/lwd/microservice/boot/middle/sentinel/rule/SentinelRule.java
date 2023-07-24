package org.lwd.microservice.boot.middle.sentinel.rule;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.circuitbreaker.CircuitBreakerStrategy;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 资源控制规则---内存模式
 * @author weidong
 * @version V1.0.0
 * @since 2023/7/17
 */
//@Configuration
public class SentinelRule {

    public static void initSentinelRule(){
        initFlowQpsRule();
        initDegradeRule();
        initSystemProtectionRule();
    }

    /**
     * 流量控制规则
     *
     * Field	    说明	                        默认值
     * resource	    资源名，资源名是限流规则的作用对象
     * count	    限流阈值
     * grade	    限流阈值类型，QPS 或线程数模式	    QPS 模式
     * limitApp	    流控针对的调用来源	            default，代表不区分调用来源
     * strategy	    调用关系限流策略：直接、链路、关联	根据资源本身（直接）
     * controlBehavior	流控效果（直接拒绝 / 排队等待 / 慢启动模式），不支持按调用关系限流	 直接拒绝
     */
//    @PostConstruct
    private static void initFlowQpsRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello");
        // Set max qps to 20
        flowRule.setCount(5);
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setLimitApp("default");
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    /**
     * 熔断降级规则
     *
     * Field	    说明	                                                                                默认值
     * resource	    资源名，即规则的作用对象
     * grade	    熔断策略，支持慢调用比例/异常比例/异常数策略	                                                慢调用比例
     * count	    慢调用比例模式下为慢调用临界 RT（超出该值计为慢调用）；异常比例/异常数模式下为对应的阈值
     * timeWindow	熔断时长，单位为 s
     * minRequestAmount	熔断触发的最小请求数，请求数小于该值时即使异常比率超出阈值也不会熔断（1.7.0 引入）	        5
     * statIntervalMs	统计时长（单位为 ms），如 60*1000 代表分钟级（1.8.0 引入）	                            1000 ms
     * slowRatioThreshold	慢调用比例阈值，仅慢调用比例模式有效（1.8.0 引入）
     */
    private static void initDegradeRule() {
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule("circuitBreaker")
        .setGrade(CircuitBreakerStrategy.ERROR_RATIO.getType())
        .setCount(0.1) // Threshold is 70% error ratio
        .setMinRequestAmount(5)
        .setStatIntervalMs(5000) // 5s
        .setTimeWindow(20);
        rules.add(rule);
        DegradeRuleManager.loadRules(rules);
    }

    /**
     * 系统保护规则 (SystemRule)
     * Sentinel 系统自适应限流从整体维度对应用入口流量进行控制，结合应用的 Load、CPU 使用率、总体平均 RT、入口 QPS 和并发线程数等几个维度的监控指标，
     * 通过自适应的流控策略，让系统的入口流量和系统的负载达到一个平衡，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。
     *
     * Field	            说明	                            默认值
     * highestSystemLoad	load1 触发值，用于触发自适应控制阶段	-1 (不生效)
     * avgRt	            所有入口流量的平均响应时间	            -1 (不生效)
     * maxThread	        入口流量的最大并发数	                -1 (不生效)
     * qps	                所有入口资源的 QPS	                -1 (不生效)
     * highestCpuUsage	    当前系统的 CPU 使用率（0.0-1.0）	    -1 (不生效)
     */
    private static void initSystemProtectionRule() {
        List<SystemRule> rules = new ArrayList<>();
        SystemRule rule = new SystemRule();
        rule.setHighestSystemLoad(10);
        rules.add(rule);
        SystemRuleManager.loadRules(rules);
    }

    /**
     * 来源访问控制（黑白名单）
     *
     * 我们需要根据调用方来限制资源是否通过，这时候可以使用 Sentinel 的黑白名单控制的功能。
     * 黑白名单根据资源的请求来源（origin）限制资源是否通过，若配置白名单则只有请求来源位于白名单内时才可通过；
     * 若配置黑名单则请求来源位于黑名单时不通过，其余的请求通过。
     *
     * 调用方信息通过 ContextUtil.enter(resourceName, origin) 方法中的 origin 参数传入。
     *
     * 黑白名单规则（AuthorityRule）非常简单，主要有以下配置项：
     *
     * resource：资源名，即限流规则的作用对象
     * limitApp：对应的黑名单/白名单，不同 origin 用 , 分隔，如 appA,appB
     * strategy：限制模式，AUTHORITY_WHITE 为白名单模式，AUTHORITY_BLACK 为黑名单模式，默认为白名单模式
     *
     */
    private static void initAuthorityRule(){
        AuthorityRule rule = new AuthorityRule();
        rule.setResource("test");
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rule.setLimitApp("appA,appB");
        AuthorityRuleManager.loadRules(Collections.singletonList(rule));
    }



}
