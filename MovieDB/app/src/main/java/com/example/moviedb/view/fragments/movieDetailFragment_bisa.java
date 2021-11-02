package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.adapter.ProCompanyAdapter;
import com.example.moviedb.dialogLoading;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.view.MainMenuActivity;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link movieDetailFragment_bisa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class movieDetailFragment_bisa extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public movieDetailFragment_bisa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment movieDetailFragment_bisa.
     */
    // TODO: Rename and change types and number of parameters
    public static movieDetailFragment_bisa newInstance(String param1, String param2) {
        movieDetailFragment_bisa fragment = new movieDetailFragment_bisa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }
    private TextView title_details_movie, avg_details_movie, overview_details_movie,
            tagline_details_movie, popularitas_details_movie, tglRilis_details_movie,
            genre_details_movie;
            private ImageView background_details_movie, poster_details_movie,background_details_movie2;
            private RecyclerView rv_card_details_companies;
            private MovieViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail_bisa, container, false);
        tglRilis_details_movie = view.findViewById(R.id.tglRilis_details_movie);
        title_details_movie = view.findViewById(R.id.title_details_movie);
        avg_details_movie = view.findViewById(R.id.avg_details_movie);
        tagline_details_movie = view.findViewById(R.id.tagline_details_movie);
        overview_details_movie = view.findViewById(R.id.overview_details_movie);
        popularitas_details_movie = view.findViewById(R.id.popularitas_details_movie);
        genre_details_movie = view.findViewById(R.id.genre_details_movie);
        poster_details_movie = view.findViewById(R.id.poster_details_movie);
        background_details_movie = view.findViewById(R.id.poster_details_movie);
        tglRilis_details_movie = view.findViewById(R.id.tglRilis_details_movie);
        // rv_now_playing_fragment = view.findViewById(R.id.rv_now_playing_fragment);
        rv_card_details_companies = view.findViewById(R.id.rv_card_details_companies);
        background_details_movie2= view.findViewById(R.id.background_details_movie2);


        String MovieId = getArguments().getString("movieId");
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getMovieById(MovieId);
        viewModel.getResultGetMovieById().observe(getActivity(), showResultMovie);
        return view;
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String Gtitle = movies.getTitle();
            String Goverview = movies.getOverview();
            String Grelease = movies.getRelease_date();
            String Gpopuler = String.valueOf(movies.getPopularity());
            String Gavg = String.valueOf(movies.getVote_average());
            String Gvote = String.valueOf(movies.getVote_count());
            String Gposter = movies.getPoster_path().toString();
            String Gbackround = movies.getBackdrop_path();
            String Gtagline = movies.getTagline();

            LinearLayoutManager tampilProCom = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            rv_card_details_companies.setLayoutManager(tampilProCom);
            ProCompanyAdapter adapter = new ProCompanyAdapter(getActivity());
            adapter.setProductionCompaniesList(movies.getProduction_companies());
            rv_card_details_companies.setAdapter(adapter);

            String genre = "";
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if (i == movies.getGenres().size() - 1) {
                    genre += movies.getGenres().get(i).getName();
                } else {
                    genre += movies.getGenres().get(i).getName() + ", ";
                }
            }

            //KURANG RILIS dan tagline, populer
            tagline_details_movie.setText(Gtagline);
            genre_details_movie.setText(genre);
            popularitas_details_movie.setText(Gpopuler);
            tglRilis_details_movie.setText(Grelease);
            overview_details_movie.setText(Goverview);
            title_details_movie.setText(Gtitle);
            avg_details_movie.setText(Gavg + "" + "(" + Gvote + ")");
            Glide.with(movieDetailFragment_bisa.this).load(Const.IMG_URL + Gposter).into(poster_details_movie);
            Glide.with(movieDetailFragment_bisa.this).load(Const.IMG_URL + Gbackround).into(background_details_movie2);
        }
    };
}