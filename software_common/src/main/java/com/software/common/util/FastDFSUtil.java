package com.software.common.util;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FastDFSUtil {

    @Value("${fastdf.ip}")
    public  String ip;


    //图片上传
    public  String uploadPic(byte [] file,String extName){

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

            String[] uploadFile = storageClient.upload_file(file, 0, file.length, extName, null);

            return ip+uploadFile[0]+"/"+uploadFile[1];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return "";
    }

    //文件上传
    public  String upload(byte [] file,String extName){

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

        String[] uploadFile = storageClient.upload_file(file, 0, file.length, extName, null);

        return uploadFile[0]+"-"+uploadFile[1];

        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return "";
    }

    //文件下载
    public  byte [] download(String groupName,String remoteFilename) {
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
