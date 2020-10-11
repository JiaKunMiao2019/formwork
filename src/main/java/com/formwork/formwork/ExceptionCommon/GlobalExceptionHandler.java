/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: GlobalExceptionHandler
 * Author:   莉莉
 * Date:     2020/7/19 19:44
 * Description: 统一异常处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.ExceptionCommon;

/**
 * 〈一句话功能简述〉<br> 
 * 〈统一异常处理〉
 *
 * @author 莉莉
 * @create 2020/7/19
 * @since 1.0.0
 */

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**全局异常处理类*/
@ControllerAdvice
public class GlobalExceptionHandler {
    //JDK中的自带的日志API
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public BaseResult doHandleRuntimeException(
            RuntimeException e){
        e.printStackTrace();//也可以写日志
        return new BaseResult(e);//封装异常信息
    }
}