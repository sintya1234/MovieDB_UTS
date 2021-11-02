package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.viewmodel.MovieViewModel;

public class MovieDetailsActivity extends AppCompatActivity {


    private TextView lbl_text,lbl_title_movie_details,lbl_overview_movie_details,lbl_tgl_rilis_movie_details, lbl_tgl_genre_movie_details;
    private MovieViewModel viewModel;
//    private String movie_img_details;
    private ImageView lbl_img_movie_details;
    private String movie_id="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent =getIntent();
        viewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        movie_id=intent.getStringExtra("movieId");
        viewModel.getMovieById(movie_id);

        //movie_title=intent.getStringExtra("movie_title");
        //movie_overview=intent.getStringExtra("movie_overview");
        //movie_release_date=intent.getStringExtra("movie_release_date");
        viewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);
        //movie_img_details=intent.getStringExtra("movie_gambar");


        lbl_text=findViewById(R.id.lbl_movie_details);
        lbl_title_movie_details=findViewById(R.id.lbl_title_movie_details);
        lbl_overview_movie_details=findViewById(R.id.lbl_overview_movie_details);
        lbl_tgl_rilis_movie_details=findViewById(R.id.lbl_tgl_rilis_movie_details);
        lbl_img_movie_details=findViewById(R.id.lbl_img_movie_details);
        lbl_tgl_genre_movie_details=findViewById(R.id. lbl_tgl_genre_movie_details);

        lbl_text.setText((movie_id));
        //lbl_title_movie_details.setText((movie_title));
        //lbl_overview_movie_details.setText((movie_overview));
        //lbl_tgl_rilis_movie_details.setText((movie_release_date));
       // lbl_img_movie_details.setImageResource();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String movie_title = movies.getTitle();
            String movie_overview = movies.getOverview();
            String movie_release_date = movies.getRelease_date();
            String img_path = movies.getPoster_path().toString();
            String movie_genre="";

            for(int i=0;i<movies.getGenres().size();i++){
                movie_genre += movies.getGenres().get(i).getName();
                if(i !=movies.getGenres().size()-1){
                    movie_genre += ", ";
                }
            }
            lbl_tgl_genre_movie_details.setText(movie_genre);
            lbl_title_movie_details.setText(movie_title);
            lbl_overview_movie_details.setText(movie_overview);
            lbl_tgl_rilis_movie_details.setText(movie_release_date);
            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + img_path).into(lbl_img_movie_details);
        }
    };
}