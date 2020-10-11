/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: ExceptionCommon
 * Author:   莉莉
 * Date:     2020/4/18 21:03
 * Description: 异常处理的公共方法
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ExceptionCommon;

/**
 * 〈异常处理的公共方法〉
 *
 * @author 莉莉
 * @create 2020/4/18
 * @since 1.0.0
 */
public class ExceptionCommon extends Exception{
    public ExceptionCommon() {
    }

    public ExceptionCommon(String message) {
        super(message);
    }

    public ExceptionCommon(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionCommon(Throwable cause) {
        super(cause);
    }

    public ExceptionCommon(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}