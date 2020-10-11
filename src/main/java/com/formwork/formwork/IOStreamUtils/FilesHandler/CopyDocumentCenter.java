/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: CopyDocumentCenter
 * Author:   莉莉
 * Date:     2020/10/11 11:24
 * Description: 文件复制中心
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.IOStreamUtils.FilesHandler;

import com.formwork.formwork.ExceptionCommon.ExceptionCommon;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈文件复制中心〉
 *
 * @author 莉莉
 * @create 2020/10/11
 * @since 1.0.0
 */
@Service
public class CopyDocumentCenter {

    //文件复制的最基础的框架
    public void copyDocumentforPath(String oldPath,String copyPath) throws Exception {
        //新建输入输出流
        BufferedInputStream  in  = null;
        BufferedOutputStream out = null;
        try {

            //文件加密解密处理
            //校验
                //安全性校验
                //格式校验
                //文件大小校验
                //等等
            //根据校验返回的service进行分类处理
                //文件类复制处理
                    //判断是文件还是文件夹
            File oldFile = new File(oldPath);
            File copyFile = new File(copyPath);
            //如果是文件夹就新建文件夹
            if (oldFile.isDirectory()){
                File newPathFile = new File(copyPath, oldPath);
                newPathFile.mkdirs();
            }else {
                //获取读取读出的流
                Map map = getInAndOutStream(oldFile, copyFile);
                in  = (BufferedInputStream) map.get("in");
                out = (BufferedOutputStream) map.get("out");
               //执行复制操作
                copyDocument(in,out,oldFile,copyFile);
            }
            //音频类复制处理
                //视频类复制处理
        }catch (IOException e){
            throw new ExceptionCommon("复制文件失败",e);
        }finally {
            //4.资源关闭
            //要求：先关闭外层的流，再关闭内层的流
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



    }

    /** 执行复制操作*/
    private void copyDocument(BufferedInputStream in,BufferedOutputStream out,File oldFile, File copyFile) throws IOException {
        //开始进行复制,设置每次读取的字节
        byte[] bytes = new byte[1024];
        int len;//定义下标
        while ((len = in.read(bytes)) != -1){
            out.write(bytes,0,len);
        }
    }

    /** 新建缓冲流*/
    private Map getInAndOutStream(File oldFile, File copyFile) throws FileNotFoundException {
        HashMap<String, Object> map = new HashMap<>();
        FileInputStream inStr = new FileInputStream(oldFile);
        FileOutputStream outStr = new FileOutputStream(copyFile);
        BufferedInputStream in = new BufferedInputStream(inStr);
        BufferedOutputStream out = new BufferedOutputStream(outStr);
        map.put("in",in);
        map.put("out",out);
        return map;
    }
}