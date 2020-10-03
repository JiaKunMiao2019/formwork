/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer5
 * Author:   莉莉
 * Date:     2020/9/18 18:05
 * Description: 使用BlockingQueue进行协同操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 〈使用BlockingQueue进行协同操作〉
 *
 * @author 莉莉
 * @create 2020/9/18
 * @since 1.0.0
 */
public class ProducerAndConcumer5 {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<Object> queue = new LinkedBlockingDeque<>(3);

        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Concumer concumer1 = new Concumer(queue);
        Concumer concumer2 = new Concumer(queue);
        Concumer concumer3 = new Concumer(queue);

        producer1.start();
        producer2.start();
        concumer1.start();
        concumer2.start();
        concumer3.start();

        producer1.join();
        producer2.join();
        concumer1.join();
        concumer3.join();
        concumer2.join();
    }

    static class Producer extends Thread {
        BlockingQueue<Integer> queue;

        public Producer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    int value = new Random().nextInt();
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName()+"Producing:"+value);
                    queue.put(value);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Concumer extends Thread {
        BlockingQueue<Integer> queue;

        public Concumer(BlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while(true){
                    Thread.sleep(400);
                    System.out.println(Thread.currentThread().getName()+"Concumer"+queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}