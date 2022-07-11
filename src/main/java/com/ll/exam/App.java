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

            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "종료":
                    break outer;
                case "등록":
                    registerJsonFile();
                    break;
                case "목록":
                    readJsonFile();
                    break;
                case "삭제":
                    // URL에 입력된 id 얻기
                    int paramId = rq.getIntParam("id", 0);
                    // URL에 입력된 id가 없다면 작업중지
                    if (paramId == 0) {
                        System.out.println("id를 입력해주세요.");
                        continue;
                    }
                    deleteJsonFile(paramId);
                case "수정":
                    updateJsonFile(cmd);
                    break;
            }
        }
        sc.close();
    }

    public static void updateJsonFile(String cmd) throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        String id = cmd.split("=")[1];
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

    public static void deleteJsonFile(int id) {
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
        System.out.println("번호 / 명언 / 작가");
        System.out.println("---------------------");

        File dir = new File("./json_db");
        File files[] = dir.listFiles();

        for (File f : files) {
            if (f.toString().contains("db_info.json")) {
                continue;
            } else {
                FileReader reader = new FileReader(f.toString());
                Object ob = new JSONParser().parse(reader);

                JSONObject js = (JSONObject) ob;

                String id = ((Long)js.get("id")).toString();
                String content = (String) js.get("content");
                String name = (String) js.get("name");

                System.out.println(id + " / " + content + " / " + name);
            }
        }
    }

    public static void registerJsonFile() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("작가 : ");
        String name = sc.nextLine().trim();

        long newIdOfJsonFile = getCurrentIdOfDbInfo() + 1;

        JSONObject jo = new JSONObject();
        jo.put("id", newIdOfJsonFile);
        jo.put("content", content);
        jo.put("name", name);

        String jsonStr = jo.toString();
        File jsonFile = new File("./json_db/" + newIdOfJsonFile + ".json");

        writeStringToFile(jsonStr, jsonFile);
        updateCurrentIdOfDbInfo();
    }

    public static long getCurrentIdOfDbInfo() throws IOException, ParseException {
        File file = new File("./json_db/db_info.json");
        FileReader reader = new FileReader(file);
        Object ob = new JSONParser().parse(reader);

        JSONObject js = (JSONObject) ob;

        long id = (Long)js.get("current_id");

        return id;
    }

    public static void updateCurrentIdOfDbInfo() throws IOException, ParseException {
        File dbInfoFile = new File("./json_db/db_info.json");
        FileReader reader = new FileReader(dbInfoFile);
        Object ob = new JSONParser().parse(reader);

        JSONObject js = (JSONObject) ob;

        long id = (Long)js.get("current_id");
        long newId = id + 1;
        js.put("current_id", newId);

        writeStringToFile(js.toJSONString(), dbInfoFile);
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}