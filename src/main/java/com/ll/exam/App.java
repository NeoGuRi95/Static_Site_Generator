package com.ll.exam;

import java.io.*;
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
                default:
                    if (cmd.matches("삭제\\?id=[0-9]+$")) {
                        deleteJsonFile(cmd);
                    } else if (cmd.matches("수정\\?id=[0-9]+$")) {
                        updateJsonFile(cmd);
                    }
                    break;
            }
        }
        sc.close();
    }

    public static void updateJsonFile(String cmd) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        String id = cmd.split("=")[1].toString();
        String updateFileName = ".\\json_db\\" + id + ".json";
        File updateFile = new File(updateFileName);

        if (updateFile.exists()) {
            System.out.println(id + "번 명언을 수정합니다.");
            FileReader reader = new FileReader(updateFileName);
            Object ob = new JSONParser().parse(reader);
            JSONObject js = (JSONObject) ob;

            System.out.println("기존 명언 : " + js.get("content"));
            System.out.print("새 명언 : ");
            String newContent = sc.nextLine();

            js.put("content", newContent);
            writeStringToFile(js.toJSONString(), updateFile);
            System.out.println(id + "번 명언이 수정되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    public static void deleteJsonFile(String cmd) {
        String id = cmd.split("=")[1].toString();
        String deleteFileName = ".\\json_db\\" + id + ".json";
        File deleteFile = new File(deleteFileName);

        if (deleteFile.exists()) {
            deleteFile.delete();
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
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

    public static void registerJsonFile() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String name = sc.nextLine().trim();

        long newIdOfJsonFile = getCurrentIdOfJsonFile() + 1;

        JSONObject jo = new JSONObject();
        jo.put("id", newIdOfJsonFile);
        jo.put("content", content);
        jo.put("name", name);

        String jsonStr = jo.toString();
        File jsonFile = new File("./json_db/" + newIdOfJsonFile + ".json");

        writeStringToFile(jsonStr, jsonFile);
    }

    public static long getCurrentIdOfJsonFile() throws IOException, ParseException {
        File file = new File("./json_db/db_info.json");
        FileReader reader = new FileReader(file);
        Object ob = new JSONParser().parse(reader);

        JSONObject js = (JSONObject) ob;

        long id = (Long)js.get("current_id");

        return id;
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}