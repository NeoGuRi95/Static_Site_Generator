package com.ll.exam;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void countJsonFileTest() throws IOException, ParseException {
        long rs = App.getCurrentIdOfJsonFile();
        Assertions.assertEquals(2, rs);
    }
}
