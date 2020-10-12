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

import com.formwork.formwork.DepandentCommon.ConstantCommon;
import com.formwork.formwork.ExceptionCommon.ExceptionCommon;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * 〈文件复制中心〉
 *
 * @author 莉莉
 * @create 2020/10/11
 * @since 1.0.0
 */
@Service
public class CopyDocumentCenter {
    static final List<String> FORM_DATE = Arrays.asList("测试一","测试二");
    /**文件的上传处理（Excel-模板类的）*/
    public void upLoadExcel(HttpServletRequest request) throws Exception {
        String suffix = null;
        // 转型为MultipartHttpRequest
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //获得文件
        MultipartFile file = multipartRequest.getFile("file");
        if (file == null){
            throw new ExceptionCommon("上传的文件为空，请重新上传");
        }
        //获得文件名，格式校验
        String fileName = file.getOriginalFilename();
        if (!StringUtils.isEmpty(file.getOriginalFilename()) && !StringUtils.isEmpty(fileName)){
            suffix = file.getOriginalFilename().substring(fileName.lastIndexOf("."), fileName.length());
        }else {
            throw new ExceptionCommon(ConstantCommon.FORMAT_EROR);
        }
        //进行excel的文件处理
        Workbook workbook =null;
        if (ConstantCommon.XLS.equals(suffix) || ConstantCommon.XLSX.equals(suffix)){
            workbook = new HSSFWorkbook(file.getInputStream());
        }else{
            throw new ExceptionCommon(ConstantCommon.FORMAT_EROR);
        }
        doExcelRead(workbook);
    }

    private void doExcelRead(Workbook workbook) throws Exception {
        if (workbook.getNumberOfSheets() > 0){
            Sheet sheet = workbook.getSheetAt(0);
            Row tittle = sheet.getRow(0);
            //校验tittle
            doValidatFormatTittle(tittle);

            //读取excel的每行的文字并返回数据
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {//先读行
                Row row = sheet.getRow(i);
                if (row == null){
                    continue;
                }
                boolean isAllBlank = true;
                for (int j = 0; j < 5; j++) {
                    if (row.getCell(j) != null){
                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        if (!StringUtils.isEmpty(row.getCell(j).getStringCellValue())){
                            isAllBlank = false;
                        }
                    }
                }
                if (isAllBlank){
                    System.out.println("第"+i+"行为空");
                    continue;
                }
                //读取每行的数据并进行数据库数据保存
                String index1 = StringUtils.strip(Optional.ofNullable(row.getCell(0)).map(Cell::getStringCellValue).orElse(""));
                String index2 = StringUtils.strip(Optional.ofNullable(row.getCell(1)).map(Cell::getStringCellValue).orElse(""));
            }

        }else {
            throw new ExceptionCommon("xls文件的sheet数据丢失");
        }
    }

    private void doValidatFormatTittle(Row tittle) throws Exception {
        if (tittle !=null && (tittle.getLastCellNum() >= FORM_DATE.size())){
            int lastCellNum = tittle.getLastCellNum();
            for (int i = 0; i < lastCellNum; i++) {
               String t = Optional.ofNullable(tittle.getCell(i)).map(Cell::getStringCellValue).orElse("");
               if (i < FORM_DATE.size() && !FORM_DATE.get(i).equals(t)){
                   throw new ExceptionCommon("模板不是最新模板，请下载最新的模板");
               }
            }
        }else {
            throw new ExceptionCommon("模板不是最新模板，请下载最新的模板");
        }
    }

    /**文件复制的最基础的框架*/
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