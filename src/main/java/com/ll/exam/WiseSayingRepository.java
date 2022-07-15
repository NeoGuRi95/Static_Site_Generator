package com.ll.exam;

public class WiseSayingRepository {
    public int getNewId() {
        int newId = Util.readNumberFromFile("./jsonDB/DB_Info.txt", 9999);
        return newId;
    }
}
