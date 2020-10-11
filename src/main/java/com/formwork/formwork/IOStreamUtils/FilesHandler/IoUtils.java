/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: IoUtils
 * Author:   莉莉
 * Date:     2020/10/11 10:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.IOStreamUtils.FilesHandler;

import java.io.File;
import java.io.IOException;

/**
 * 〈〉
 *
 * @author 莉莉
 * @create 2020/10/11
 * @since 1.0.0
 */
public class IoUtils {
    //回滚练习1
    public static void main(String[] args) {
        File file = new File("D:/DocumentProcessingCenter");
        if (!file.exists()){// 如果D:/DocumentProcessingCenter/DocumentCopyCenter不存在，就创建为目录
            file.mkdirs();
        }

        // 创建以dir1为父目录,名为"dir2"文件夹的File对象
        File file1 = new File(file, "DocumentCopyCenter");
        if (!file1.exists()){
            file1.mkdir();
        }

        //创建file1为父目录，名为file2文件的file对象
        File file2 = new File(file1, "copyLog.txt");
        if (!file2.exists()){
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}