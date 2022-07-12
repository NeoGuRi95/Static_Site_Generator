package com.ll.exam;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> wiseSayings;
    private int wiseSayingLastId;

    public WiseSayingRepository() {
        wiseSayings = new ArrayList<>();
        wiseSayingLastId = 0;
    }

    public WiseSaying findById(int paramId) {
        for (WiseSaying wiseSaying : wiseSayings) {
            if (wiseSaying.id == paramId) {
                return wiseSaying;
            }
        }
        return null;
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public WiseSaying write(String content, String author) {
        int id = ++wiseSayingLastId;
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    public void remove(WiseSaying wiseSaying) {
        wiseSayings.remove(wiseSaying);
    }

    public void modify(int id, String newContent, String newAuthor) {
        WiseSaying wiseSaying = findById(id);
        wiseSaying.content = newContent;
        wiseSaying.author = newAuthor;
    }
}
