package com.galaxy.ftp;

import com.galaxy.util.FTPUtil;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangpeng
 * Date: 2018/6/5
 * Time: 22:36
 */
public class FTPTest {

    @Test
    public void testUploadFile() throws IOException {
        File localFile = new File("/Users/pengwang/Documents/Desktop/416156.jpg");
        System.out.println(FTPUtil.uploadFile(localFile));

        InputStream inputStream = FTPUtil.downloadFile("416156.jpg");
        System.out.println(inputStream.read());

        System.out.println(FTPUtil.deleteFile("416156.jpg","/home/"));
    }

    @Test
    public void testDel() throws IOException{
        FileUtils.forceDelete(new File("/Users/pengwang/Documents/AONE工作文档/PrintForm.pdf"));
    }
}
