package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.NowPlaying;
import com.example.moviedb.model.Upcoming;

import java.util.List;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.CardViewViewHolder>{
    private Context contex;
    private List<Upcoming.Results> ListUpcoming;
    private List<Upcoming.Results> getListUpcoming(){
        return ListUpcoming;
    }
    public void setListUpcoming(List<Upcoming.Results>ListUpcoming){
        this.ListUpcoming=ListUpcoming;
    }

    public UpcomingAdapter(Context contex){
        this.contex=contex;
    }

    public UpcomingAdapter(List<Upcoming.Results> listUpcoming) {
        ListUpcoming = listUpcoming;
    }


    @NonNull
    @Override
    public UpcomingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return  new UpcomingAdapter.CardViewViewHolder(view);

}

    @Override
    public void onBindViewHolder(@NonNull UpcomingAdapter.CardViewViewHolder holder, int position) {

        final Upcoming.Results results = getListUpcoming().get(position);
        holder.lbl_title.setText(results.getTitle());
        holder.lbl_overview.setText(results.getOverview());
        holder.lbl_release_date.setText(results.getRelease_date());
        Glide.with(contex)
                .load(Const.IMG_URL + results.getPoster_path())
                .into(holder.img_poster);

    }

    @Override
    public int getItemCount() {
        return getListUpcoming().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView img_poster;
        TextView lbl_title,lbl_overview,lbl_release_date;
        CardView cv;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            img_poster=itemView.findViewById((R.id.img_poster_card_nowplaying));
            lbl_title=itemView.findViewById(R.id.lbl_title_card_nowplaying);
            lbl_overview=itemView.findViewById(R.id.lbl_overview_card_nowplaying);
            lbl_release_date=itemView.findViewById(R.id.lbl_releasdate_card_nowplaying);
            cv=itemView.findViewById(R.id.cv_card_nowplaying);
        }
    }
}
