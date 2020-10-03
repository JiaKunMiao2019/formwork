/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: LockUtils
 * Author:   莉莉
 * Date:     2020/9/7 16:55
 * Description: 对外提供lock锁
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ThreadFormWork.ProducerAndConcumerModel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈对外提供lock锁〉
 *
 * @author 莉莉
 * @create 2020/9/7
 * @since 1.0.0
 */
public class LockUtils {
    private static ConcurrentHashMap<String, Object> map;

    private String key;

    public LockUtils(String key) {
        this.key = key;
        synchronized (this) {
            if (null == map) {
                map = new ConcurrentHashMap<>();
            }
            if (null == map.get(key)) {
                ReentrantLock lock = new ReentrantLock();
                //Condition condition = lock.newCondition();
                map.put(key,lock);
                //map.put(key+condition,condition);
            }
        }
    }
    public ConcurrentHashMap<String,Object> getMap(){
        return map;
    }

    public void setMap(String key,String condition){
        if ((null != map) && (null != map.get(key)) && (null != map.get(condition))){
            ReentrantLock lock = (ReentrantLock) map.get(key);
            Condition value = lock.newCondition();
            map.put(condition,value);
        }
    }
}