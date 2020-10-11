package com.formwork.formwork;

import com.formwork.formwork.ApplictionBootstrap.FormworkApplication;
import com.formwork.formwork.IOStreamUtils.FilesHandler.CopyDocumentCenter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest()
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = FormworkApplication.class)
class FormworkApplicationTests {

    @Autowired
    private CopyDocumentCenter copyDocumentCenter;
    @Test
    void contextLoads() throws Exception {
        String path = "D:\\DocumentProcessingCenter\\DocumentCopyCenter\\copyLog.txt";
        String copyPath = "D:\\DocumentProcessingCenter\\DocumentCopyCenter\\log.txt";

        copyDocumentCenter.copyDocumentforPath(path,copyPath);
    }

}
