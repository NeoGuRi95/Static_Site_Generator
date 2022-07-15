package com.ll.exam;

public class WiseSayingRepository {
    public int getNewId() {
        int newId = Util.readNumberFromFile("jsonDB/DB_Info.txt", 9999);
        return newId;
    }

    public boolean write(WiseSaying wiseSaying) {
        if (wiseSaying == null) return false;
        Util.saveToFile("jsonDB/" + wiseSaying.getId() + ".json", wiseSaying.toJson());
        Util.saveNumberToFile("jsonDB/DB_Info.txt", wiseSaying.getId() + 1);
        return true;
    }
}
