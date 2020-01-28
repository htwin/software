package com.software.common;

import com.software.common.util.FastDFSUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.runner.RunWith;

import java.io.IOException;

public class Test {

    public static void main(String[] args) {
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

            String[] uploadFile = storageClient.upload_file("C:\\Users\\ht\\Desktop\\temp\\video.mp4", "mp4", null);

            System.out.println(uploadFile[0]+"/"+uploadFile[1]);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
