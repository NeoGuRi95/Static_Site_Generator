package com.ll.exam;

import java.util.Scanner;

public class App {
    private Scanner sc;

    public App(Scanner sc) {
        this.sc = sc;
    }

    public void run() {
        System.out.println("==명언 SSG 시작==");
        WiseSayingController wiseSayingController = new WiseSayingController(sc);

        outer:
        while (true) {

            System.out.print("명령) ");
            String cmd = sc.nextLine();
            Rq rq = new Rq(cmd);

            switch (rq.getPath()) {
                case "등록":
                    wiseSayingController.write();
                    break;
                case "목록":
                    wiseSayingController.read();
                    break;
                case "삭제":
                    wiseSayingController.remove(rq);
                    break;
                case "수정":
                    wiseSayingController.update(rq);
                    break;
                case "종료":
                    break outer;
            }
        }

        System.out.println("==프로그램이 종료됩니다==");
    }
}
