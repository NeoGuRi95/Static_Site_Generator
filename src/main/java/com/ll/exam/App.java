package com.ll.exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    Map<String, String> map = new HashMap<>();

    public void run() {
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
                    String article = sc.nextLine().trim();
                    System.out.print("작가 : ");
                    String who = sc.nextLine().trim();
                    map.put(article, who);
                    System.out.println(map.size() + "번 명언이 등록되었습니다.");
                    break;
            }
        }

        sc.close();
    }
}