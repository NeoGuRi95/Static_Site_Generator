package com.ll.exam;

import java.util.Map;

public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public WiseSaying(Map<String, Object> map) {
        this.id = (Integer)map.get("id");
        this.content = (String) map.get("content");
        this.author = (String) map.get("author");
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toJson() {
        String js = """
                {
                    "id": %d,
                    "content": "%s",
                    "author": "%s"
                }
                """.stripIndent().formatted(this.id, this.getContent(), this.getAuthor());
        return js.trim();
    }
}
