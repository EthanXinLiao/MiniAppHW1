package com.example.xinliao.miniapphw1;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Xin Liao on 2/18/2018.
 */

public class Movie {
    // instance variables or fields
    public String title;
    ArrayList<String> actor;
    public String description;
    public String imageUrl;
    public String instructionUrl;
    public String sees;
    public int episode;

    public static ArrayList<Movie> getMoviesFromFile (String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        try{
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each recipe in your array

            for (int i = 0; i < movies.length(); i++){
                Movie movie = new Movie();
                movie.title = movies.getJSONObject(i).getString("title");
                movie.description = movies.getJSONObject(i).getString("description");
                movie.imageUrl = movies.getJSONObject(i).getString("poster");
                movie.instructionUrl = movies.getJSONObject(i).getString("url");
                movie.sees = "Has seens?";
                JSONArray main_actor = movies.getJSONObject(i).getJSONArray("main_characters");
                movie.actor = new ArrayList<>();
                for (int j=0;j<main_actor.length();j++){
                    movie.actor.add(main_actor.get(j).toString());
                }
                movie.episode = movies.getJSONObject(i).getInt("episode_number");

                // add to arraylist
               movieList.add(movie);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;

    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

}


