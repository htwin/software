package com.software.common;

import com.software.common.util.FastDFSUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.runner.RunWith;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {



    public static void main(String[] args) throws FileNotFoundException {
        try {
            //加载配置文件
            ClientGlobal.initByProperties("client.properties");

            //tracker client
            TrackerClient trackerClient = new TrackerClient();

            //tracker server
            TrackerServer trackerServer = trackerClient.getConnection();

            //storage server
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            //storage client
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            String[] upload_file = storageClient.upload_file("D:\\我\\2020毕业论文\\轮播2.png", "png", null);

            System.out.println(upload_file[0]+"/"+upload_file[1]);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

    }

}
