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
import com.example.moviedb.adapter.UpcomingAdapter;
import com.example.moviedb.dialogLoading;
import com.example.moviedb.helper.ItemClickSupport;
import com.example.moviedb.model.Upcoming;
import com.example.moviedb.viewmodel.MovieViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link upComingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class upComingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public upComingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment upComingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static upComingFragment newInstance(String param1, String param2) {
        upComingFragment fragment = new upComingFragment();
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
    private RecyclerView rv_upComingFragment;
    private MovieViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MovieViewModel.class);
        viewModel.getUpcoming();
        viewModel.getResultUpcoming().observe(getActivity(), showUpcoming);
        rv_upComingFragment = view.findViewById(R.id.rv_upComingFragment);

        return view;
    }

    private Observer<Upcoming> showUpcoming = new Observer<Upcoming>() {
        @Override
        public void onChanged(Upcoming upcoming) {
            rv_upComingFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
            UpcomingAdapter adapter = new UpcomingAdapter(getActivity());
            adapter.setListUpcoming(upcoming.getResults());
            rv_upComingFragment.setAdapter(adapter);

            ItemClickSupport.addTo(rv_upComingFragment).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                    final dialogLoading loadingBisa =  new dialogLoading (upComingFragment.this);

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
                    bundle.putString("movieId", ""+upcoming.getResults().get(position).getId());

        Navigation.findNavController(v).navigate(R.id.action_upComingFragment_to_movieDetailFragment_bisa, bundle);
            }
        });
    }
    };
}