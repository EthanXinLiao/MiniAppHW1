package com.example.xinliao.miniapphw1;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xin Liao on 2/18/2018.
 */

public class MovieAdapter extends BaseAdapter {
    // adapter takes the app itself and a list of data to display
    private Context mContext;
    private ArrayList<Movie> mMovieList;
    private LayoutInflater mInflater;

    // constructor
    public MovieAdapter(Context mContext, ArrayList<Movie> mMovieList){

        // initialize instances variables
        this.mContext = mContext;
        this.mMovieList = mMovieList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    // methods
    // a list of methods we need to override

    // gives you the number of movie in the data source
    @Override
    public int getCount(){
        return mMovieList.size();
    }

    // returns the item at specific position in the data source

    @Override
    public Object getItem(int position){
        return mMovieList.get(position);
    }

    // returns the row id associated with the specific position in the list
    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        // check if the view already exists
        // if yes, you don't need to inflate and findViewbyID again
        if (convertView == null){
            // inflate
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
            // add the views to the holder
            holder = new ViewHolder();
            // views
            holder.titleTextView = convertView.findViewById(R.id.movie_list_title);
            holder.descriptionTextView = convertView.findViewById(R.id.movie_list_description);
            holder.actorTextView =  convertView.findViewById(R.id.movie_list_actor);
            holder.seesTextView = convertView.findViewById(R.id.movie_list_sees);
            holder.thumbnailImageView = convertView.findViewById(R.id.movie_list_thumbnail);


            // add the holder to the view
            // for future use
            convertView.setTag(holder);
        }
        else{
            // get the view holder from converview
            holder = (ViewHolder)convertView.getTag();
        }

        // get relavate subview of the row view
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        TextView actorTextView = holder.actorTextView;
        TextView seesTextView = holder.seesTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;


        // get corresonpinding movie for each row
        Movie movie = (Movie)getItem(position);


        titleTextView.setText(movie.title);
        descriptionTextView.setText(movie.description);
        seesTextView.setText(movie.sees);
        int display = 3;
        if(movie.actor.size()>=display){
            String actor = "";
            for(int i = 0; i < display; i++){
                actor += movie.actor.get(i);
                if(i < display - 1){
                    actor += ", ";
                }
            }
            actorTextView.setText(actor);
        }




        // imageView
        // use Picasso library to load image from the image url
        Picasso.with(mContext).load(movie.imageUrl).into(thumbnailImageView);
        return convertView;
    }

    // viewHolder
    // is used to customize what you want to put into the view
    // it depends on the layout design of your row
    // this will be a private static class you have to define
    private static class ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public TextView actorTextView;
        public TextView seesTextView;
        public ImageView thumbnailImageView;

    }


    // intent is used to pass information between activities
    // intent -> pacakge
    // sender, receiver


}
