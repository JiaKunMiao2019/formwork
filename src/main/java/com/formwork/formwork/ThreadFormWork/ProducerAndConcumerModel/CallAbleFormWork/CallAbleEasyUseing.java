/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CallAbleEasyUseing
 * Author:   莉莉
 * Date:     2020/9/18 18:37
 * Description: CallAble的简单使用
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.CallAbleFormWork;

import com.formwork.formwork.ThreadFormWork.ThreadPoolFormWork.ThreadPoolFormWork;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.currentThread;

/**
 * 〈CallAble的简单使用〉
 *
 * @author 莉莉
 * @create 2020/9/18
 * @since 1.0.0
 */
public class CallAbleEasyUseing {
    public static void main(String[] args) {
        ThreadDemo tb = new ThreadDemo();

        // //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果
        FutureTask<Integer> task = new FutureTask<>(tb);
        //ThreadPoolFormWork.getPool().submit(task);
        //2.接收线程运算后的结果
        new Thread(task).start();
        Integer sum = null;
        //FutureTask 可用于 闭锁 类似于CountDownLatch的作用，
        // 在所有的线程没有执行完成之后这里是不会执行的
        try {
            sum = task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println("------------------------------------");

    }

    static class ThreadDemo implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {

            int sum = 0;

            for (int i = 0; i <= 10; i++) {
                sum += i;
                System.out.println(currentThread().getName() + i);
            }

            return sum;
        }

    }
}