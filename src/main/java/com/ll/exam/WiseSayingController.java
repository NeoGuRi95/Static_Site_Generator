package com.ll.exam;

import java.util.Scanner;

public class WiseSayingController {
    private Scanner sc;
    private WiseSayingService wiseSayingService;

    public WiseSayingController(Scanner sc) {
        this.sc = sc;
        wiseSayingService = new WiseSayingService();
    }

    public void write() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가 : ");
        String author = sc.nextLine();
        int id = wiseSayingService.getNewId();

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        boolean rs = wiseSayingService.write(wiseSaying);

        if (rs) System.out.println("save complete");
        else System.out.println("save fail");
    }
}
