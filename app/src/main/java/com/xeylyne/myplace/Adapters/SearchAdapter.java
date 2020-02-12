package com.xeylyne.myplace.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xeylyne.myplace.Activities.DetailActivity;
import com.xeylyne.myplace.Models.ResultPlace;
import com.xeylyne.myplace.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    Context context;
    ArrayList<ResultPlace> results;

    public SearchAdapter(Context context, ArrayList<ResultPlace> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ResultPlace result = results.get(position);
        holder.txtPlaceName.setText(result.getNAMEPLACE());
        holder.txtAddress.setText(result.getADDRESSPLACE());
        holder.txtAvgPrice.setText(result.getAVGPRICE());
        holder.txtAuthor.setText("Uploaded by : " + result.getNICKNAME()+"#"+result.getIDUSER());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("name", result.getNAMEPLACE());
                intent.putExtra("address", result.getADDRESSPLACE());
                intent.putExtra("avgprice", result.getAVGPRICE());
                intent.putExtra("review", result.getREVIEW());
                intent.putExtra("favfood", result.getFAVFOOD());
                intent.putExtra("author", result.getNICKNAME()+"#"+result.getIDUSER());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgLogo;
        TextView txtPlaceName, txtAddress, txtAvgPrice, txtAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgLogo = itemView.findViewById(R.id.imgLogoCafe);
            txtPlaceName = itemView.findViewById(R.id.txtPlaceName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtAvgPrice = itemView.findViewById(R.id.txtDetailAvgPrice);
            txtAuthor = itemView.findViewById(R.id.txtAuthor);
        }
    }
}
