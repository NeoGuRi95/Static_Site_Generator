package com.ll.exam;

import java.util.List;

public class WiseSayingService {
    private WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
    }

    public int getNewId() {
        return wiseSayingRepository.getNewId();
    }

    public boolean write(WiseSaying wiseSaying) {
        boolean rs = wiseSayingRepository.write(wiseSaying);
        return rs;
    }

    public String readFile(String path) {
        return wiseSayingRepository.readFile(path);
    }

    public List<String> readAllFiles() {
        return wiseSayingRepository.readAllFiles();
    }

    public boolean remove(int id) {
        return wiseSayingRepository.remove(id);
    }

    public void update(WiseSaying ws) {
        wiseSayingRepository.update(ws);
    }
}
