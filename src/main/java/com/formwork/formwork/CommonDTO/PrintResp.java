/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PrintResp
 * Author:   莉莉
 * Date:     2020/10/13 18:51
 * Description: 读取图片流的返回模型
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.CommonDTO;

/**
 * 〈读取图片流的返回模型〉
 *
 * @author 莉莉
 * @create 2020/10/13
 * @since 1.0.0
 */

public class PrintResp {
    private String code;
    private String message;

    //base64 的文件流
    private String fileDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFileDate() {
        return fileDate;
    }

    public void setFileDate(String fileDate) {
        this.fileDate = fileDate;
    }
}