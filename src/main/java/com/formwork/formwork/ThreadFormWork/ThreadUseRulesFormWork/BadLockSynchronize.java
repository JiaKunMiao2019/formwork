/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: BadLockSynchronize
 * Author:   莉莉
 * Date:     2020/9/7 10:46
 * Description: 关于死锁的示例
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ThreadUseRulesFormWork;

/**
 * 〈关于死锁的示例〉
 *  死锁的定义：线程A持有锁a，同时在等待另外一把锁b，
 *  但是等待的这把锁b被另一个线程B持有，
 *  同时这个线程B也在等待锁a，这时两个线程同时陷入了等待的状态
 *
 *  解决的思路
 *  jps+jstack结合源代码排查
 *  检查每个线程的lock，和condition的锁的名称进行排查，看是否有死锁的现象
 *
 *  代码结构的优化
 *  所有的资源都以相同的顺序获得锁
 *  实际上，在复杂程序中，这一点很难发现
 *
 * @author 莉莉
 * @create 2020/9/7
 * @since 1.0.0
 */
public class BadLockSynchronize {
    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            synchronized (lock1){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2){
                    System.out.println("这个线程：{}"+Thread.currentThread().getName()+"已完成");
                }
            }
        }).start();

        synchronized (lock2){
            Thread.sleep(100);
            synchronized (lock1){
                System.out.println("这个线程：{}"+Thread.currentThread().getName()+"已完成");
            }
        }
    }
}