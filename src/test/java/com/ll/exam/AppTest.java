package com.ll.exam;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void getCurrentIdOfDbInfo_Test() throws IOException, ParseException {
        long rs = App.getCurrentIdOfDbInfo();
        assertEquals(2, rs);
    }

    @Test
    public void scanner_Test() {
        String input = """
                등록
                낄낄낄
                원숭이
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner sc = new Scanner(in);

        String cmd = sc.nextLine().trim();
        String content = sc.nextLine().trim();
        String name = sc.nextLine().trim();

        assertEquals("등록", cmd);
        assertEquals("낄낄낄", content);
        assertEquals("원숭이", name);
    }

    @Test
    public void register_Test() throws IOException, ParseException {
        String input = """
               hi
               외국인
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        App.registerJsonFile();
    }

    @Test
    public void update_Test() throws IOException, ParseException {
        String input = """
               수정?id=6
               벌레를 먹지마라
                """.stripIndent();
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner sc = new Scanner(System.in);

//        InputStream in = new ByteArrayInputStream("수정?id=6".getBytes());
//        System.setIn(in);
//
//        in = new ByteArrayInputStream("벌레를 먹지마라".getBytes());
//        System.setIn(in);

        App app = new App();
        app.run();
    }
}
