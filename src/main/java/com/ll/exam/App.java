package com.ll.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class App {
    public void run() throws JSONException, IOException {
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
                    System.out.print("명언 : ");
                    String content = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String person = sc.nextLine().trim();

                    JSONObject jo = new JSONObject();
                    jo.put("name", content);
                    jo.put("person", person);

                    String jsonStr = jo.toString();
                    File jsonFile = new File("./example.json");

                    writeStringToFile(jsonStr, jsonFile);
                    break;
            }
        }

        sc.close();
    }

    public static void writeStringToFile(String str, File file) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}