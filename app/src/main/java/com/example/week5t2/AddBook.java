package com.example.week5t2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.week5t2.adapter.BookAdapter;
import com.example.week5t2.db.Book;
import com.example.week5t2.db.BookDB;
import com.example.week5t2.db.BookDao;
import com.example.week5t2.db.DataConverter;
import com.example.week5t2.viewmodel.BookRespository;
import com.example.week5t2.viewmodel.BookViewModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddBook extends AppCompatActivity {
//    private static final int THUMBNAIL_SIZE = 200;
    EditText editTitle,editAuthor;
    ImageView booksrcImage;
    Button buttonAdd,cacleInput;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    BookDao bookDao;
    BookAdapter adapter;
    private String bookUri;
    private BookViewModel bookViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        bookDao = BookDB.getDBInstance(this).getBookDao();

        editTitle = findViewById(R.id.editTitle);
        editAuthor = findViewById(R.id.editAuthor);
        booksrcImage = findViewById(R.id.booksrcImage);
//        bmpImage = null;
        booksrcImage.setOnClickListener(v -> openGallery());

    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            Uri imageUri = data.getData();
            bookUri = getImageuri(String.valueOf(imageUri));
            bookViewModel.insert();                             // insert or save the image uri to database
        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK)
//        {
//            Uri imageUri = data.getData();
//            try {
//                Bitmap bitmap = getThumbnail(imageUri);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    public Bitmap getThumbnail(Uri uri) throws FileNotFoundException, IOException {
//        InputStream input = this.getContentResolver().openInputStream(uri);
//
//        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
//        onlyBoundsOptions.inJustDecodeBounds = true;
////        onlyBoundsOptions.inDither=true;//optional
////        onlyBoundsOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
//        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
//        input.close();
//
//        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
//            return null;
//        }
//
//        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
//
//        double ratio = (originalSize > THUMBNAIL_SIZE) ? (originalSize / THUMBNAIL_SIZE) : 1.0;
//
//        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
//        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
//        bitmapOptions.inDither = true; //optional
//        bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//
//        input = this.getContentResolver().openInputStream(uri);
//        bmpImage= BitmapFactory.decodeStream(input, null, bitmapOptions);
//        input.close();
//        return bmpImage;
//    }
//
//    private static int getPowerOfTwoForSampleRatio(double ratio){
//        int k = Integer.highestOneBit((int)Math.floor(ratio));
//        if(k==0) return 1;
//        else return k;
//    }
    public void onAddButtonClick(View view){
        if(editTitle.getText().toString().isEmpty() ||
                editAuthor.getText().toString().isEmpty() ||
                bmpImage == null){
            Toast.makeText(
                    this,
                    "Error: User Data is missing",
                    Toast.LENGTH_SHORT
            ).show();
        } else {
            Book book = new Book();
            book.setBookTitle(editTitle.getText().toString());
            book.setBookAuthor(editAuthor.getText().toString());
            book.setBookImageuri(bookUri);                      // need find the image data rather than uri
            bookDao.insert(book);
            Toast.makeText(
                    this,
                    "Insertion successful",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }


}