package com.shuanger.demo.filterinterceptor.filter;

import java.io.*;

public class StreamUtils {

    private static final int KB = 1024;

    public static String streamToString(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buf = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = bis.read(buf)) != -1) {
            String temp = new String(buf, 0, len);
            sb.append(temp);
        }
        return sb.toString();
    }

    public OutputStream stringToStream(String data) throws IOException {
        if (data == null) {
            return null;
        }
        byte[] byteData = data.getBytes();
        int size = byteData.length / KB + 2;
        BufferedOutputStream bos = new BufferedOutputStream(new ByteArrayOutputStream(), size);
        bos.write(byteData);
        return bos;
    }

    /**
     * 将字符串保存到本地磁盘
     *
     */
    public static boolean StringToFile(String data, String path, String fileName) {
        if (data == null || path == null || fileName == null) {
            throw new RuntimeException("非法传参");
        }
        String filePath = path + "/" + fileName;
        File file = new File(path);
        boolean flag = false;
        // 将密钥对写入到文件
        BufferedWriter bw = null;
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            FileWriter fw = new FileWriter(filePath);
            bw = new BufferedWriter(fw);
            bw.write(data);
            bw.flush();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 将文件中的内容读到内存中
     */
    public static String fileToString(String filePath) {
        if (filePath == null) {
            throw new RuntimeException("文件名不能为空");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("没有找到指定的文件路径");
        }
        BufferedReader br = null;
        String data = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String buf;
            StringBuilder sb = new StringBuilder();
            while ((buf = br.readLine()) != null) {
                sb.append(buf);
            }
            data = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    /**
     * 将输入流转换成字节数组
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4 * KB];
        int n = 0;
        while (-1!=(n=input.read(buffer))){
            output.write(buffer,0,n);
        }
        return output.toByteArray();
    }

    public static boolean byteArrayToFile(byte[] data, String path) throws IOException {
        if(data==null){
            return false;
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
        bos.write(data);
        bos.flush();
        return true;
    }




}

