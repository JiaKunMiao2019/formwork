/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ProducerAndConcumer2
 * Author:   莉莉
 * Date:     2020/9/8 13:53
 * Description: 生产者消费者使用lock和condition实现的模式
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockCondition;

import com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel.LockUtils;

/**
 * 〈生产者消费者使用lock和condition实现的模式〉
 *
 * @author 莉莉
 * @create 2020/9/8
 * @since 1.0.0
 */
public class ProducerAndConcumer2 {
   static class SetLock{
        static LockUtils user = new LockUtils("User");
        void foo(){
            user.setMap("User","Empty");
            user.setMap("User","Full");
            user.setMap("User","Producer");
            user.setMap("User","Concumer");
        }
    }

    static class Producer{

    }

}