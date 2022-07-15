package com.ll.exam;

import java.util.List;

public class WiseSayingRepository {
    public int getNewId() {
        int newId = Util.readNumberFromFile("jsonDB/DB_Info.txt", 0);
        return newId;
    }

    public boolean write(WiseSaying wiseSaying) {
        if (wiseSaying == null) return false;
        Util.saveToFile("jsonDB/" + wiseSaying.getId() + ".json", wiseSaying.toJson());
        Util.saveNumberToFile("jsonDB/DB_Info.txt", wiseSaying.getId() + 1);
        return true;
    }

    public List<String> readAllFiles() {
        return Util.getFileNamesFromDir("jsonDB/");
    }

    public String readFile(String path) {
        return Util.readFromFile(path);
    }

    public boolean remove(int id) {
        String path = "jsonDB/" + id + ".json";
        if (Util.existsFile(path)){
            Util.deleteDir("jsonDB/" + id + ".json");
            return true;
        } else {
            return false;
        }
    }

    public void update(WiseSaying ws) {
        String path = "jsonDB/" + ws.getId() + ".json";
        Util.saveToFile(path, ws.toJson());
    }
}
