/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ThreadCas
 * Author:   莉莉
 * Date:     2020/9/1 21:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ThreadUseRulesFormWork;

/**
 * 修饰实例方法，作用于当前实例加锁，进入同步代码前要首先获得当前实例的锁
 * 修饰静态方法，作用于当前类对象加锁，进入同步方法之前首先要获取当前类对象的锁，这种用类对象加锁的方式实际上为了解决多个实例对象加锁的情况下带来的线程不安全的情况
 * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码块前要获得给定对象的锁
 * 在类中如果加锁了的非静态方法和枷锁的静态方法同时访问时是线程安全的，但是如果其中一个没有枷锁就是非线程安全的，实质上静态和非静态的锁对于一个对象来说是两把锁
 * synchronized作用于实例方法
 * 其实从内存模型上可以理解一下，静态方法是放在方法去单独存放是所有实例共享的数据，而堆中存放的是实例地址
 * 并且静态块只有类被加载的时候加载一次
 *
 * @author 莉莉
 * @create 2020/9/1
 * @since 1.0.0
 */
public class SynchronizeUse {
    //锁住方法
    public synchronized void test1(){
        foo(5);
    }

    //锁住实例
    public void test2(){
        synchronized (this){
           foo(5);
        }
    }

    //锁住静态块
    static synchronized void test3(){
        foo(5);
    }

    private static void foo(int i){

        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        SynchronizeUse cas = new SynchronizeUse();
        SynchronizeUse cas2= new SynchronizeUse();

        Thread thread1 = new Thread(() -> {cas.test2();},"a方法锁1");
        Thread thread2 = new Thread(() -> {SynchronizeUse.test3();},"静态锁1");
        Thread thread6 = new Thread(() -> {SynchronizeUse.test3();},"静态锁2");
        Thread thread3 = new Thread(() -> {cas.test1();},"a实例锁1");
        Thread thread4 = new Thread(() -> {cas2.test1();},"b实例锁1");


        thread1.start();
        //thread2.start();
        thread3.start();
        //thread4.start();
        //thread5.start();
        //thread6.start();
        //通过几种不同的方案可以得出以下结论：
        // 1.实例锁是一个实例共享的锁，对象锁是每个实例共享的
        // 2.在非静态方法上和直接锁住实例的的为实例锁，静态方法的锁为对象锁
    }
}