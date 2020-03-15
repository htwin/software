package com.software.common;

import com.software.common.util.FastDFSUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.runner.RunWith;

import java.io.*;

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

            byte[] file = storageClient.download_file("group1", "M00/00/00/wKhihF5JCD-ACjnSAACBPhdy35o647.jpg");

            File f = new File("D:/temp/2.jpg");
            FileOutputStream outputStream = new FileOutputStream(f);

            outputStream.write(file);

            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

}
