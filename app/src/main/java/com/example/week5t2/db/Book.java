package com.example.week5t2.db;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BookTable")
public class Book {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private String bookImageuri;




    public int getId() {
        return id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBookImageuri() {
        return bookImageuri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBookImageuri(String bookImageuri) {
        this.bookImageuri = bookImageuri;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                '}';
    }
}


