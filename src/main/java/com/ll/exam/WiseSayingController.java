package com.ll.exam;

import java.util.List;
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

        if (rs) System.out.println(id + "번 명언이 등록되었습니다.");
        else System.out.println("명언 등록이 실패했습니다.");
    }

    public void read() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<String> fileNames = wiseSayingService.readAllFiles();
        WiseSaying ws;
        for (String fileName : fileNames) {
            if (fileName.endsWith(".json")) {
                String json = wiseSayingService.readFile("jsonDB/" + fileName);
                ws = new WiseSaying(Util.jsonToMap(json));
                System.out.println(ws.getId() + " / " + ws.getAuthor() + " / " + ws.getContent());
            }
        }
    }

    public void remove(Rq rq) {
        int id = rq.getValue("id", 0);
        boolean rs = wiseSayingService.remove(id);
        if (rs) System.out.println(id + "번 명언이 삭제되었습니다.");
        else System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void update(Rq rq) {
        int id = rq.getValue("id", 0);
        String path = "jsonDB/" + id + ".json";
        String json = wiseSayingService.readFile(path);
        WiseSaying ws = new WiseSaying(Util.jsonToMap(json));
        System.out.println("명언(기존) : " + ws.getContent());
        String newContent = sc.nextLine();
        System.out.println("작가(기존) : " + ws.getAuthor());
        String newAuthor = sc.nextLine();
        ws.setContent(newContent);
        ws.setAuthor(newAuthor);
        wiseSayingService.update(ws);
    }
}
