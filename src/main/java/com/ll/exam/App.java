package com.ll.exam;

import java.io.*;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
    private Scanner sc;

    public App() {
        sc = new Scanner(System.in);
    }

    public void run() throws IOException, ParseException {
        System.out.println("== 명언 SSG ==");

        outer:
        while (true) {
            System.out.printf("명령) ");
            String cmd = sc.nextLine().trim();

            Rq rq = new Rq(cmd);
            int paramId;

            switch (rq.getPath()) {
                case "종료":
                    break outer;
                case "등록":
                    registerJsonFile();
                    break;
                case "목록":
                    printJsonFileAll();
                    break;
                case "삭제":
                    deleteJsonFile(rq);
                    break;
                case "수정":
                    updateJsonFile(rq);
                    break;
            }
        }
        sc.close();
    }
    // 수정
    public void updateJsonFile(Rq rq) throws IOException, ParseException {
        // URL에 입력된 id 얻기
        int id = rq.getIntParam("id", 0);
        // URL에 입력된 id가 없다면 작업중지
        if (id == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
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
    // 삭제
    public void deleteJsonFile(Rq rq) {
        // URL에 입력된 id 얻기
        int id = rq.getIntParam("id", 0);
        // URL에 입력된 id가 없다면 작업중지
        if (id == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }
        String deleteFileName = ".\\json_db\\" + id + ".json";
        File deleteFile = new File(deleteFileName);

        if (deleteFile.exists()) {
            deleteFile.delete();
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }
    // 목록
    public void printJsonFileAll() throws IOException, ParseException {
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
    // 등록
    public void registerJsonFile() throws IOException, ParseException {
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
    // 현재 id 읽기
    public long getCurrentIdOfDbInfo() throws IOException, ParseException {
        File file = new File("./json_db/db_info.json");
        FileReader reader = new FileReader(file);
        Object ob = new JSONParser().parse(reader);

        JSONObject js = (JSONObject) ob;

        long id = (Long)js.get("current_id");

        return id;
    }
    // 현재 id 증가
    public void updateCurrentIdOfDbInfo() throws IOException, ParseException {
        File dbInfoFile = new File("./json_db/db_info.json");
        FileReader reader = new FileReader(dbInfoFile);
        Object ob = new JSONParser().parse(reader);

        JSONObject js = (JSONObject) ob;

        long id = (Long)js.get("current_id");
        long newId = id + 1;
        js.put("current_id", newId);

        writeStringToFile(js.toJSONString(), dbInfoFile);
    }
    // json 파일 생성
    public void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}