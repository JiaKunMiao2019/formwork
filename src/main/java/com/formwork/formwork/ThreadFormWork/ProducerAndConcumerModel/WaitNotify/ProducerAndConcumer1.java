/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer1
 * Author:   莉莉
 * Date:     2020/9/7 13:39
 * Description: 使用wait和notify协同的生产者和消费者模型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.WaitNotify;


import java.util.LinkedList;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈使用wait和notify协同的生产者和消费者模型〉
 *
 * @author 莉莉
 * @create 2020/9/7
 * @since 1.0.0
 */
public class ProducerAndConcumer1 {
    //公共数据部分
    static class Cantiner{
        volatile LinkedList<Integer> map = new LinkedList<>();
    }

    //生产者，向对象中添加数据
    static class Producer extends Thread{
        Cantiner cantiner;
        public Producer(Cantiner cantiner){
            this.cantiner = cantiner;
        }

        @Override
        public void run(){
            synchronized (cantiner){
                System.out.println("Producer is running");
                while (cantiner.map.size() > 5){
                    System.out.println("Concumer is waitting");
                    try {
                        cantiner.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int value = new Random().nextInt();
                cantiner.map.add(value);
                System.out.println("Producer Running："+Thread.currentThread().getName()+":"+cantiner.map.toString());
                cantiner.notifyAll();
                try {
                    Thread.sleep(100);//模拟新增需要一段时间进行处理
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //消费者，处理对象中的数据
    static class Concumer extends Thread{
        Cantiner cantiner;//存储对象
        String   key;     //获取目标key
        public Concumer(Cantiner cantiner){
            this.cantiner = cantiner;
        }

        @Override
        public void run(){
            System.out.println("Concumer is running");
            synchronized (cantiner){
                while (cantiner.map.isEmpty()){
                    try {
                        System.out.println("Concumer is waitting");
                        cantiner.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Concumer Running："+Thread.currentThread().getName()+":"+cantiner.map.toString());
                cantiner.map.removeFirst();
                cantiner.notifyAll();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Cantiner cantiner = new Cantiner();

        Producer producer = new Producer(cantiner);
        Concumer concumer = new Concumer(cantiner);

        producer.start();
        concumer.start();

        producer.join();
        concumer.join();
    }
}