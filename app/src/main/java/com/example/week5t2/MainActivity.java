package com.example.week5t2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.lifecycle.LiveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week5t2.adapter.BookAdapter;
import com.example.week5t2.db.Book;
import com.example.week5t2.db.BookDB;
import com.example.week5t2.db.BookDao;

import com.example.week5t2.viewmodel.BookViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.week5t2.db.Book;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button addButton,delButton;

    BookAdapter adapter;
    public  static final String DB_Name = "book_db";

    private BookViewModel bookViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Book> bookList = new ArrayList<>();

        recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new BookAdapter(bookList,this);

        bookViewModel = new ViewModelProvider(this).get(BookViewModel.class);                //   ? ?  ?
        bookViewModel.getCurrentbooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                        // update the cached copy of books in the adapter
                adapter.setBook(books);
            }
        });

        recyclerView.setAdapter(adapter);



        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAdd = new Intent(MainActivity.this,AddBook.class);
                startActivity(intentAdd);
                finish();
            }
        });

    }



}