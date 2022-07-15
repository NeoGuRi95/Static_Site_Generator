package com.ll.exam;

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
}
