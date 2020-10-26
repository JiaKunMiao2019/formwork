/**
 * Copyright (C), 2015-2020, XXX有限公司
 * FileName: PrintPDFDocument
 * Author:   莉莉
 * Date:     2020/10/14 11:39
 * Description: 打印PDF文件
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.formwork.formwork.IOStreamUtils.FilesHandler;

import com.formwork.formwork.CommonDTO.CommonRequestDTO;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈打印PDF文件〉
 *
 * @author 莉莉
 * @create 2020/10/14
 * @since 1.0.0
 */
public class PrintPDFDocument {
    public static void main(String[] args) throws IOException, DocumentException {
        CommonRequestDTO dto = new CommonRequestDTO();
        dto.setMsg("test1");
        PrintCommonDocument(dto);
    }
    //打印最简单的白板类(这个是需要使用ADobe Acrobat制作一个PDF模板)
    public static void PrintCommonDocument(CommonRequestDTO requestDTO) throws IOException, DocumentException {
        String msg = requestDTO.getMsg();
        //定义文件的路径和文件名称
        String path = "D:\\DocumentProcessingCenter\\DocumentPrintPDF\\";
        String fileName = msg + System.currentTimeMillis() + ".pdf";
        String wholePath = path + fileName;
        File file = new File(wholePath);
        file.createNewFile();
        //生产PDF模板
        PdfReader reader  = new PdfReader(wholePath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        /* 将要生成的目标PDF文件名称 */
        PdfStamper ps = new PdfStamper(reader, bos);
        PdfContentByte under = ps.getUnderContent(1);

        /* 使用中文字体 */
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);

        /* 取出报表模板中的所有字段 */
        AcroFields fields = ps.getAcroFields();
        fields.setSubstitutionFonts(fontList);
        fillData(fields, data());

        /* 必须要调用这个，否则文档不会生成的 */
        ps.setFormFlattening(true);
        ps.close();

        //生成pdf路径
        OutputStream fos = new FileOutputStream("D:/result.pdf");
        fos.write(bos.toByteArray());
        fos.flush();
        fos.close();
        bos.close();
    }
    /**
     * 填充数据源
     * 其中data存放的key值与pdf模板中的文本域值相对应
     * */
    private static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("xm", "李磊");
        data.put("pxsj", "2018年1月12日-2018年5月12日");
        data.put("rq", "2018年5月18日");
        return data;
    }

    /**
     * 填充模板
     * */
    public static void fillData(AcroFields fields, Map<String, String> data)
            throws IOException, DocumentException {
        for (String key : data.keySet()) {
            String value = data.get(key);
            fields.setField(key, value); // 为字段赋值,注意字段名称是区分大小写的
        }
    }
}