/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ThreadPoolFormWork
 * Author:   莉莉
 * Date:     2020/9/17 19:19
 * Description: 线程池的通用模板
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ThreadPoolFormWork;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 〈线程池的通用模板〉
 *
 * @author 莉莉
 * @create 2020/9/17
 * @since 1.0.0
 */
@Component
public class ThreadPoolFormWork implements InitializingBean {
    private static ExecutorService pool;                            //通用的线程池
    private static ScheduledExecutorService scheduledExecutorPool;  //执行定时任务的线程池
    private static ExecutorService mainPool;                        //主线程使用线程池
    private static ExecutorService ruleExcuPool;                    //规则类的线程池
    private static ExecutorService fixPool;                         //预定义线程池
    private static ExecutorService catchPool;                       //缓存类的线程池
    private static ExecutorService singlePool;                      //单例的线程池

    public static ExecutorService getFixPool() {
        return fixPool;
    }

    public static ExecutorService getCatchPool() {
        return catchPool;
    }

    public static ExecutorService getSinglePool() {
        return singlePool;
    }

    public static ExecutorService getPool() {
        return pool;
    }

    public static ScheduledExecutorService getScheduledExecutorPool() {
        return scheduledExecutorPool;
    }

    public static ExecutorService getMainPool() {
        return mainPool;
    }

    public static ExecutorService getRuleExcuPool() {
        return ruleExcuPool;
    }

    //实现InitializingBean，在spring容器初始化的时候加载afterPropertiesSet()方法
    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("common-pool-%d").build();
        ThreadFactory mainThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("main-pool-%d").build();
        pool = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1024), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        scheduledExecutorPool = new ScheduledThreadPoolExecutor(1,namedThreadFactory);
        mainPool = new ThreadPoolExecutor(5,200,
                0L,TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(1024),
                mainThreadFactory,new ThreadPoolExecutor.CallerRunsPolicy());
        ruleExcuPool = new ThreadPoolExecutor(5,100,0L,TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(100),namedThreadFactory,new ThreadPoolExecutor.CallerRunsPolicy());
        //预定义线程池使用完之后必须.shutdown()，否则会一直存在在内存中
        //适用于处理流量消峰
        fixPool = Executors.newFixedThreadPool(10);
        //缓存类线程池，可以重复使用线程的线程池，但是容易造成oom内存溢出
        //快速处理大量耗时较短的任务，如Netty的NIO接受请求时
        catchPool = Executors.newCachedThreadPool();
        //单例线程，适用于线程安全的处理
        singlePool =Executors.newSingleThreadExecutor();
    }
}