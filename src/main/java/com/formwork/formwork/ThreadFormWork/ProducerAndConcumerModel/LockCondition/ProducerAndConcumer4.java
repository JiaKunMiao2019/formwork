/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer4
 * Author:   莉莉
 * Date:     2020/9/18 17:51
 * Description: 信号量的使用-使用一个线程保证原子性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * 〈信号量的使用-使用一个线程保证原子性〉
 *  这个不是很了解，需要后期进行进一步学
 * @author 莉莉
 * @create 2020/9/18
 * @since 1.0.0
 */
public class ProducerAndConcumer4 {
    //定义信号量
    static Semaphore empty = new Semaphore(1);
    static Semaphore full = new Semaphore(0);

    //生产者
    static class Producer extends Thread {
        Queue<Integer> queue;

        public Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                empty.acquire();
                synchronized (ProducerAndConcumer4.class){
                    queue.add(1234);
                    full.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            try {
                full.acquire();
                synchronized (ProducerAndConcumer4.class){
                    queue.remove();
                    empty.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}