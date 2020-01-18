package com.software.common.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FastDFSUtil {

    //文件上传
    public static void upload(){

    }

    //文件下载
    public static byte [] download(String groupName,String remoteFilename) {
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

            byte[] file = storageClient.download_file(groupName, remoteFilename);

            return file;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

}
