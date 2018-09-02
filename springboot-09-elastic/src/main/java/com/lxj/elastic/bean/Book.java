package com.lxj.elastic.bean;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by liuxinjian on 2018/6/17.
 */
@Document(indexName = "liuxinjian",type = "book")
public class Book {
    private  Integer id;
    private  String bookName;
    private  String author;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
