package com.ll.exam;

import java.util.Scanner;

public class App {
    public void run(Scanner sc) {
        System.out.println("==명언 SSG 시작==");
        String cmd = sc.nextLine();
        Rq rq = new Rq(cmd);

        outer:
        while (true) {
            switch (rq.getPath()) {
                case "등록":
                    break;
                case "목록":
                    break;
                case "삭제":
                    break;
                case "수정":
                    break;
                case "종료":
                    break outer;
            }
        }

        System.out.println("==프로그램이 종료됩니다==");
    }
}
