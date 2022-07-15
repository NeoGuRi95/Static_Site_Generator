package com.ll.exam;

public class WiseSaying {
    private int id;
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
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

    public String toJson() {
        String js = """
                {
                    "id": %d,
                    "content": %s,
                    "author": %s
                }
                """.stripIndent().formatted(this.id, this.getContent(), this.getAuthor());
        return js.trim();
    }
}
