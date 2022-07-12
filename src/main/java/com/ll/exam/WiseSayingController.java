package com.ll.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;
    private Scanner sc;

    public WiseSayingController(Scanner sc) {
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
        this.sc = sc;
    }

    public void list(Rq rq) {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------");
        for (int i = wiseSayings.size() - 1; i >= 0; i--) {
            WiseSaying wiseSaying_ = wiseSayings.get(i);
            System.out.printf("%d / %s / %s\n", wiseSaying_.id, wiseSaying_.content, wiseSaying_.author);
        }
    }

    public void write(Rq rq) {
        System.out.printf("명언 : ");
        String content = sc.nextLine().trim();
        System.out.printf("작가 : ");
        String author = sc.nextLine().trim();
        int id = ++wiseSayingLastId; // 명언 글 번호 증가

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }

    public void remove(Rq rq) {
        int paramId = rq.getIntParam("id", 0);

        // URL에 입력된 id가 없다면 작업중지
        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기
        WiseSaying foundWiseSaying = findById(paramId);
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }

        // 입력된 id에 해당하는 명언객체를 리스트에서 삭제
        wiseSayings.remove(foundWiseSaying);

        System.out.printf("%d번 명언이 삭제되었습니다.\n", paramId);
    }

    public void update(Rq rq) {
        int paramId = rq.getIntParam("id", 0);

        // URL에 입력된 id가 없다면 작업중지
        if (paramId == 0) {
            System.out.println("id를 입력해주세요.");
            return;
        }

        // URL에 입력된 id에 해당하는 명언객체 찾기
        WiseSaying foundWiseSaying = findById(paramId);
        if (foundWiseSaying == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", paramId);
            return;
        }

        System.out.println("명언(기존) : " + foundWiseSaying.content);
        System.out.print("새 명언 : ");
        String newContent = sc.nextLine();

        System.out.println("작가(기존) : " + foundWiseSaying.author);
        System.out.print("새 작가 : ");
        String newAuthor = sc.nextLine();

        foundWiseSaying.content = newContent;
        foundWiseSaying.author = newAuthor;
        System.out.printf("%d번 명언이 수정되었습니다.\n", paramId);
    }

    private WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }
        return null;
    }
}
