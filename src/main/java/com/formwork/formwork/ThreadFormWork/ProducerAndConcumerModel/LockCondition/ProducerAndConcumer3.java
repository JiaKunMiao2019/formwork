/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer3
 * Author:   莉莉
 * Date:     2020/9/18 15:33
 * Description: 使用BlockingQueue
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈使用BlockingQueue〉
 *
 * @author 莉莉
 * @create 2020/9/18
 * @since 1.0.0
 */
public class ProducerAndConcumer3 {
    //定义一个最大的等待队列数
    private static final int MAX_SIZE_QUEUE = 100;

    //定义一个公共的锁
    static Lock lock = new ReentrantLock();
    //定义condition的适用
    static Condition empty = lock.newCondition();//队列为空时等待
    static Condition full = lock.newCondition();//队列达到最大队列数时等待

    //生产者
    static class Producer extends Thread {
        Queue<Integer> queue;

        public Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (queue.size() >= MAX_SIZE_QUEUE) {
                    full.await();
                }
                queue.add(1234);
                empty.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //注意使用lock的时候枷锁之后必须使用unlock进行手动解锁，
                // 而且必须放在finally中保证必须执行的第一行
                //r如果多次锁的话，必须要进行相应次数的解锁
                lock.unlock();
            }
        }
    }

    //消费者
    static class Concumer extends Thread {
        Queue<Integer> queue;

        public Concumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    empty.await();
                }
                queue.remove(1234);
                full.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Queue<Integer> queue = new LinkedList<>();

        Producer producer = new Producer(queue);
        Concumer concumer = new Concumer(queue);

        producer.start();
        concumer.start();

        producer.join();
        concumer.join();
    }
}