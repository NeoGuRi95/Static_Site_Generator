package com.ll.exam;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
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

}
