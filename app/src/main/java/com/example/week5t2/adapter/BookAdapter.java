package com.example.week5t2.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week5t2.R;
import com.example.week5t2.db.Book;
import com.example.week5t2.db.DataConverter;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Book> bookList;
    private Context context;

    public BookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context ;
    }

    public void setBookList(List<Book> bookList){
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item,
                parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

            if(bookList != null){
                Book book = bookList.get(position);
                myViewHolder.bookImage.setImageURI(Uri.parse(book.getBookImageuri()));
                myViewHolder.titleTextView.setText(book.getBookTitle());
                myViewHolder.authorTextView.setText(book.getBookAuthor());
            }else {
                myViewHolder.bookImage.setImageURI(null);
                myViewHolder.titleTextView.setText("No Books");
                myViewHolder.authorTextView.setText("No Information");
            }

    }
    public void setBook(List<Book> books){
        bookList = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(bookList != null){
            return bookList.size();
        } else
            return 0;
        }


}
