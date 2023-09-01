package com.es.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author DongXin
 * @date 2022/8/16 11:40 上午
 */
@Component
@Slf4j
public class MyJobHandler {


    @XxlJob(value = "demoJobHandler", init = "init", destroy = "destroy")
    public void execute() {

        log.info("demo 任务执行");

        String jobParam = XxlJobHelper.getJobParam();
        log.info("任务参数获取：{}", jobParam);

        // 当前分片数，从0开始，即执行器的序号
        int shardIndex = XxlJobHelper.getShardIndex();

        //总分片数，执行器集群总机器数量
        int shardTotal = XxlJobHelper.getShardTotal();

//        XxlJobHelper.handleFail("自定义返回异常");
        XxlJobHelper.handleSuccess("自定义成功信息");
    }

//    @XxlJob(value = "demoJobHandler", init = "init", destroy = "destroy")
//    public ReturnT<String> execute() {
//
//        log.info("demo 任务执行");
//
//        String jobParam = XxlJobHelper.getJobParam();
//        log.info("任务参数获取：{}", jobParam);
//
//        return ReturnT.SUCCESS;
//    }

    private void init() {
        log.info("init 方法执行成功");
    }

    private void destroy() {
        log.info("destroy 方法执行成功");
    }
}
