package com.lyyzoo.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestFastDFS {
    public static void main(String[] args) {
//        testUpload();
        testDownload();
    }
    public static void testUpload(){
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            System.out.println("network_timeout="+ClientGlobal.g_network_timeout+"ms");
            System.out.println("charset="+ClientGlobal.g_charset);
            // 创建客户端
            TrackerClient tc = new TrackerClient();
            // 连接trackerserver
            TrackerServer ts = tc.getConnection();
            if(ts == null){
                System.out.println("getConnection return null");
                return;
            }
            // 获取一个storage server
            StorageServer ss = tc.getStoreStorage(ts);
            if(ss == null){
                System.out.println("getStoreStorage return null");
            }
            //创建一个storage存储客户端
            StorageClient1 sc1 = new StorageClient1(ts, ss);

            String item = "D:\\Users\\User\\Desktop\\APP\\换补卡签\\bandicam 2021-09-18 09-26-02-373.jpg";
            String fileid;
            fileid = sc1.upload_file1(item, "jpg", null);

            System.out.println(fileid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void testDownload(){
        try {
            //加载fastdfs-client.properties配置文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker
            TrackerServer trackerServer = trackerClient.getConnection();
            //获取Stroage
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            //创建stroageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer,storeStorage);
            //下载文件
            //文件id
            String fileId = "group1/M00/00/00/wKg4ZmIwO5SATa7WAAB-FlDorVs998.jpg";
            byte[] bytes = storageClient1.download_file1(fileId);
            //使用输出流保存文件
            FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/5.jpg"));
            fileOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
