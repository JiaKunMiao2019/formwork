/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: WhriterAndReaderUtil
 * Author:   莉莉
 * Date:     2020/10/3 15:45
 * Description: 字符流的工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.IOStreamUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 〈字符流的工具类〉
 *
 * @author 莉莉
 * @create 2020/10/3
 * @since 1.0.0
 */
public class WhriterAndReaderUtil {
    //文件处理工具类主要是处理数据的传输按功能可以分为两类
    //1.读取类；2.输入类
    //按文件类型来分的话也是可以分为两类
    //1.音频视频类；2.文件类
    //文件的类型：pdf、excel、word、图片等


    public static void main(String[] args) {
        //新建一个文件类，文件的路径需要自己定义
        String path = "test.txt";
        File file = new File(path);

        //新建一个输出流
        Writer writer = null;
        try {
            writer = new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (writer != null){
                try {
                    //在关闭前会做flush操作
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}