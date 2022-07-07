package com.ll.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
    public void run() throws IOException, ParseException {
        System.out.println("== 명언 SSG ==");

        Scanner sc = new Scanner(System.in);

        outer:
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            switch (cmd) {
                case "종료":
                    break outer;
                case "등록":
                    registerJsonFile();
                    break;
                case "목록":
                    readJsonFile();
                    break;
            }
        }

        sc.close();
    }

    public static void readJsonFile() throws IOException, ParseException {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------------");

        File dir = new File("./json_db");
        File files[] = dir.listFiles();

        for (File f : files) {
            FileReader reader = new FileReader(f.toString());
            Object ob = new JSONParser().parse(reader);

            JSONObject js = (JSONObject) ob;

            String id = ((Long)js.get("id")).toString();
            String content = (String) js.get("content");
            String name = (String) js.get("name");

            System.out.println(id + " / " + content + " / " + name);
        }
    }

    public static void registerJsonFile() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String name = sc.nextLine().trim();

        int numOfJsonFile = countJsonFile();

        JSONObject jo = new JSONObject();
        jo.put("id", numOfJsonFile);
        jo.put("content", content);
        jo.put("name", name);

        String jsonStr = jo.toString();
        File jsonFile = new File("./json_db/" + numOfJsonFile + ".json");

        writeStringToFile(jsonStr, jsonFile);
    }

    public static int countJsonFile() throws IOException {
        File dir = new File("./json_db");
        File files[] = dir.listFiles();

        return files.length + 1;
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}