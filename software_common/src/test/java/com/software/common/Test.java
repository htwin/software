package com.software.common;

import com.software.common.util.FastDFSUtil;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.runner.RunWith;

import java.io.IOException;

public class Test {



    public static void main(String[] args) {
        FastDFSUtil fastDFSUtil = new FastDFSUtil();
        byte[] group1s = fastDFSUtil.download("group1", "M00/00/00/wKhihF4uiDiAF67aAAAAGO7opU0322.txt");
        System.out.println(group1s);

    }

}
