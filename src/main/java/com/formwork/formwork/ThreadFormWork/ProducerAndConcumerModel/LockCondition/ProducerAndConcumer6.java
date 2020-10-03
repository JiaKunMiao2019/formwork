/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer6
 * Author:   莉莉
 * Date:     2020/9/18 18:24
 * Description: 使用Exchange类进行两个线程的数据交互
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Exchanger;

/**
 * 〈使用Exchange类进行两个线程的数据交互〉
 *可用于两个线程之间交换信息。可简单地将Exchanger对象理解为一个包含两个格子的容器，
 * 通过exchanger方法可以向两个格子中填充信息。
 * 当两个格子中的均被填充时，该对象会自动将两个格子的信息交换，
 * 然后返回给线程，从而实现两个线程的信息交换。
 * @author 莉莉
 * @create 2020/9/18
 * @since 1.0.0
 */
public class ProducerAndConcumer6 {
    //首先新建一个锁Exchanger
    private static Exchanger exchanger = new Exchanger();
    static class Producer extends Thread {
        Exchanger exchanger;

        public Producer(Exchanger exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(11111);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Concumer extends Thread {
        Exchanger exchanger;

        public Concumer(Exchanger exchanger) {
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                exchanger.exchange(null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}