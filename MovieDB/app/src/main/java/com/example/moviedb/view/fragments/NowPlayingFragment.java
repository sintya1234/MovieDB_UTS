package com.example.moviedb.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviedb.R;
import com.example.moviedb.adapter.NowPlayingAdapter;
import com.example.moviedb.dialogLoading;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.view.activities.MainActivity;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NowPlayingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowPlayingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NowPlayingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowPlayingFragment newInstance(String param1, String param2) {
        NowPlayingFragment fragment = new NowPlayingFragment();
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

    private RecyclerView rv_now_playing_fragment, rv_card_details_companies;
    private MovieViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getNowPlaying();
        viewModel.getResultNowPlaying().observe(getActivity(), showNowPlaying);
        rv_now_playing_fragment=view.findViewById(R.id.rv_now_playing_fragment);
        return view;
    }


    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {

            rv_now_playing_fragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
            adapter.setListNowPlaying(nowPlaying.getResults());
            rv_now_playing_fragment.setAdapter(adapter);


            ItemClickSupport.addTo(rv_now_playing_fragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    final dialogLoading loadingBisa =  new dialogLoading (NowPlayingFragment.this);

                    loadingBisa.startLoadingDialog();
                    Handler handler= new Handler();
                    handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                loadingBisa.dismissDialog();
                                            }
                                        },
                        1000);

                    Bundle bundle = new Bundle();
                    bundle.putString("movieId", "" + nowPlaying.getResults().get(position).getId());
                    Navigation.findNavController(v).navigate(R.id.action_nowPlayingFragment_to_movieDetailFragment_bisa, bundle);
                }
            });
        }
    };
}



