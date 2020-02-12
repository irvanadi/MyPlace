package com.xeylyne.myplace.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.xeylyne.myplace.Utilities.DataHelper;
import com.xeylyne.myplace.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    EditText edPlaceName, edAddress, edFavFood, edAvgPrice, edReview;
    DataHelper dataHelper;
    ImageView imgPick;
    Bitmap selectedImage;

    private static int GALLERY_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dataHelper = new DataHelper(this);

        edPlaceName = findViewById(R.id.edPLaceName);
        edAddress = findViewById(R.id.edAddress);
        edFavFood = findViewById(R.id.edFavFood);
        edAvgPrice = findViewById(R.id.edAvgPrice);
        edReview = findViewById(R.id.edReview);
        imgPick = findViewById(R.id.imgPick);

        findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                byte[] image = DataHelper.getBytes(selectedImage);

                boolean inserted = dataHelper.AddPlace(edPlaceName.getText().toString(), edAddress.getText().toString(), edFavFood.getText().toString(),
                        Integer.parseInt(edAvgPrice.getText().toString()), edReview.getText().toString(), image);
                if (inserted){
                    Toast.makeText(AddActivity.this, "Data Baru sudah disimpan", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "Data ada yang error, coba cek lagi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        findViewById(R.id.btnImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickImage();
            }
        });
    }

    private void PickImage() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
                imgPick.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
