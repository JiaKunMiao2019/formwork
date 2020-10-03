/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LockCommon
 * Author:   莉莉
 * Date:     2020/9/8 14:03
 * Description: 对外提供锁机制的生产
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 〈对外提供锁机制的生产〉
 *
 * @author 莉莉
 * @create 2020/9/8
 * @since 1.0.0
 */
@Component
public class LockCommon implements InitializingBean, ApplicationContextAware {
    ApplicationContext applicationContext;
    @Override
    public void afterPropertiesSet() throws Exception {
        LockUtils user = new LockUtils("User");
        user.setMap("User","Empty");
        user.setMap("User","Full");
        user.setMap("User","Producer");
        user.setMap("User","Concumer");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}