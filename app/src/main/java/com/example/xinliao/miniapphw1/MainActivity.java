package com.example.xinliao.miniapphw1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Context mContext;


    private ArrayList<Movie> movieList;
    private MovieAdapter adapter;
    private TextView seesTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        movieList = Movie.getMoviesFromFile("movies.json", this);

        adapter = new MovieAdapter(this, movieList);

        mListView = findViewById(R.id.movie_list_view);
        mListView.setAdapter(adapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Movie selectedMovie = movieList.get(position);

                Intent detailIntent = new Intent(mContext, MovieDetailActivity.class);

                detailIntent.putExtra("title", selectedMovie.title);
                detailIntent.putExtra("poster", selectedMovie.imageUrl);
                detailIntent.putExtra("description", selectedMovie.description);
                detailIntent.putExtra("position", position);


                startActivityForResult(detailIntent,1);

            }
        });
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) { // SECOND ACTIVITY IS SENDING DATA
                boolean alreadySeen = data.getBooleanExtra("alreadySeen", false);
                boolean wantToSee = data.getBooleanExtra("wantToSee", false);
                boolean donotLike = data.getBooleanExtra("donotLike",false);
                int position = data.getIntExtra("position",0);
                seesTextView=findViewById(R.id.movie_list_sees);

                if (alreadySeen){

                    movieList.get(position).sees ="Already seen";

                }

                else if (wantToSee){

                    movieList.get(position).sees ="Want to see";

                }

                else if(donotLike && !alreadySeen && !wantToSee){

                    movieList.get(position).sees ="Do not like";

                }

                else{

                    seesTextView.setText("Xin liao？");


                }

               adapter.notifyDataSetChanged();
            }

        }
    }

}


