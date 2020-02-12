package com.xeylyne.myplace.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.xeylyne.myplace.R;

public class DetailActivity extends AppCompatActivity {

    String author;
    TextView txtCafeName, txtAddress, txtAvgPrice, txtFavFood, txtReview, txtAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtCafeName = findViewById(R.id.txtTitleItem);
        txtAddress = findViewById(R.id.txtAddress);
        txtAvgPrice = findViewById(R.id.txtDetailAvgPrice);
        txtFavFood = findViewById(R.id.txtDetailFavoriteFoods);
        txtReview = findViewById(R.id.txtDetailReview);
        txtAuthor = findViewById(R.id.txtAuthor);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        final String Address = intent.getStringExtra("address");
        String AvgPrice = intent.getStringExtra("avgprice");
        String FavFood = intent.getStringExtra("favfood");
        String review = intent.getStringExtra("review");

        if (intent.getStringExtra("author") != null){
            author = intent.getStringExtra("author");
            txtAuthor.setText(author);
        }

        txtCafeName.setText(Name);
        txtAddress.setText(Address);
        txtAvgPrice.setText(AvgPrice);
        txtFavFood.setText(FavFood);
        txtReview.setText(review);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(Name);

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.trans));

        findViewById(R.id.txtMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
                intent.putExtra("alamat",Address);
                startActivity(intent);
            }
        });
    }
}

