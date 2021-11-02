package com.example.moviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedb.R;
import com.example.moviedb.helper.Const;
import com.example.moviedb.model.Movies;
import com.example.moviedb.model.NowPlaying;

import java.util.List;

public class ProCompanyAdapter extends RecyclerView.Adapter<ProCompanyAdapter.CardViewHolderProCompany>{

    private Context contex;
    private List<Movies.ProductionCompaniesDTO> ProductionCompaniesList;

    public ProCompanyAdapter(Context contex) {
        this.contex = contex;
    }

    public ProCompanyAdapter(List<Movies.ProductionCompaniesDTO> productionCompaniesList) {
        ProductionCompaniesList = productionCompaniesList;
    }

    public List<Movies.ProductionCompaniesDTO> getProductionCompaniesList() {
        return ProductionCompaniesList;
    }

    public void setProductionCompaniesList(List<Movies.ProductionCompaniesDTO> productionCompaniesList) {
        ProductionCompaniesList = productionCompaniesList;
    }

    @NonNull
    @Override
    public ProCompanyAdapter.CardViewHolderProCompany onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_production_company,parent, false);
        return new ProCompanyAdapter.CardViewHolderProCompany(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProCompanyAdapter.CardViewHolderProCompany holder, int position) {
        final Movies.ProductionCompaniesDTO productionCompaniesDTO = getProductionCompaniesList().get(position);
        holder.namaCom=productionCompaniesDTO.getName();
        holder.logoCom=productionCompaniesDTO.getLogo_path();

        Glide.with(contex)
                .load(Const.IMG_URL + holder.logoCom)
                .into(holder.img_card_production);

        holder.cardView_production.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), holder.namaCom, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return getProductionCompaniesList().size();
    }

    public class CardViewHolderProCompany extends RecyclerView.ViewHolder {

       CardView cardView_production;
       ImageView img_card_production;
       String logoCom;
       String namaCom;

        public CardViewHolderProCompany(@NonNull View itemView) {
            super(itemView);
            cardView_production=itemView.findViewById(R.id.cardView_production);
            img_card_production=itemView.findViewById(R.id.img_card_production);

        }
    }
}
