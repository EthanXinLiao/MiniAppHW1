package com.example.xinliao.miniapphw1;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Xin Liao on 2/18/2018.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private Context mContext;
    private Button mGobackButton;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView thumbnailImageView;
    private ScrollView scrollView;

    private boolean alreadySeen;
    private boolean wantToSee;
    private boolean donotLike;


    // override onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        scrollView = findViewById(R.id.scrollView);
        mContext = this;
        titleTextView = scrollView.findViewById(R.id.movie_list_title);
        descriptionTextView = scrollView.findViewById(R.id.movie_list_description);
        thumbnailImageView = scrollView.findViewById(R.id.movie_list_thumbnail);
        String title = this.getIntent().getExtras().getString("title");
        setTitle(title);
        String description = this.getIntent().getExtras().getString("description");
        String imageUrl = this.getIntent().getExtras().getString("poster");
        final int position = this.getIntent().getExtras().getInt("position");
        titleTextView.setText(title);
        descriptionTextView.setText(description);
        Picasso.with(mContext).load(imageUrl).into(thumbnailImageView);


        mGobackButton = scrollView.findViewById(R.id.gobackButton);
        mGobackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // construct intent
                Intent mGobackButtonIntent = new Intent();

                mGobackButtonIntent.putExtra("alreadySeen", alreadySeen);
                mGobackButtonIntent.putExtra("wantToSee", wantToSee);
                mGobackButtonIntent.putExtra("donotLike", donotLike);
                mGobackButtonIntent.putExtra("position", position);
                // send back to main activity
                setResult(RESULT_OK, mGobackButtonIntent);

                finish();


            }
        });


    }

    public void alreadySeen (View view) {
        alreadySeen = ((RadioButton) view).isChecked();
    }

    public void wantToSee(View view) {
        wantToSee = ((RadioButton) view).isChecked();
    }

    public void donotLike(View view) {
        donotLike = ((RadioButton) view).isChecked();
    }
}




