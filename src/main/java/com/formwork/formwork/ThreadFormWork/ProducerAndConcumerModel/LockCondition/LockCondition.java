/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LockCondition
 * Author:   莉莉
 * Date:     2020/9/7 15:15
 * Description: lock和condition的协同问题处理，基本使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈lock和condition的协同问题处理，基本使用〉
 *
 * @author 莉莉
 * @create 2020/9/7
 * @since 1.0.0
 */
public class LockCondition {
    public static void main(String[] args) {
        ReentrantLock lock  = new ReentrantLock();//创建一个公共使用的锁
        Condition condition = lock.newCondition();//由公共锁创建线程的处理机制，可以允许有多个

        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();//创建数据保存区
        AtomicInteger count = new AtomicInteger(10);//创建线程的执行次数

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    int value = new Random().nextInt();
                    map.put(value,value);
                    count.decrementAndGet();
                    condition.signal();
                }finally {
                    lock.unlock();//锁可以进行多次的lock，但是相应的需要unlock的次数
                }
            }).start();
        }

        lock.lock();
        try {
            while (count.get()>0){
                //必须使用while形成一个阻塞的效果，如果使用if的话在下次被其他线程唤醒后就无法再次wait了
                System.out.println(count.get());
                condition.await();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            System.out.println("释放了锁");
            lock.unlock();
        }
        System.out.println(map);
    }
}