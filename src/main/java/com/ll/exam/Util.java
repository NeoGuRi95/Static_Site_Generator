package com.ll.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Util {
    public static void saveToFile(String path, String body) {
        try (RandomAccessFile stream = new RandomAccessFile(path, "rw");
             FileChannel channel = stream.getChannel()) {
            byte[] strBytes = body.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
            buffer.put(strBytes);
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {

        }
    }

    public static void mkdir(String path) {
        File dir = new File(path);
        dir.mkdirs();
    }

    public static String readFromFile(String path) {
        try (RandomAccessFile reader = new RandomAccessFile(path, "r")) {
            String line = null;
            String body = "";

            while ( (line = reader.readLine()) != null ){
                body += new String(line.getBytes("iso-8859-1"), "utf-8") + "\n";
            }
            return body.trim();
        } catch (IOException e) {
        }

        return "";
    }
}