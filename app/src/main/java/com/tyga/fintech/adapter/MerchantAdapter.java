package com.tyga.fintech.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tyga.fintech.R;
import com.tyga.fintech.model.Merchant;

import java.util.ArrayList;
import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantAdapter.CategoryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Merchant merchant);
    }

    private Context context;
    private OnItemClickListener listener;
    private List<Merchant> listMerchant;
    private String status, category;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_merchant, viewGroup, false);
//        return new CategoryViewHolder(itemRow);
        return null;
    }

    public MerchantAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.listener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        categoryViewHolder.bind(getListMerchant().get(position), listener);
//

    }



    @Override
    public int getItemCount() {
        return getListMerchant().size();
    }

    public List<Merchant> getListMerchant() {
        return listMerchant;
    }

    public void setListMerchant(List<Merchant> listMerchant) {
        this.listMerchant = listMerchant;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView judul;
        CardView mCard;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
//            mCard = itemView.findViewById(R.id.favorite_card);
        }

        public void bind(final Merchant merchant, final OnItemClickListener listener) {
//            judul.setText(merchant.getNama());
//        Glide.with(context)
//                .load("https://image.tmdb.org/t/p/w185/"+getListMerchant().get(position).getPoster_path())
//                .apply(new RequestOptions())
//                .placeholder(R.drawable.placeholder)
//                .into(imgPhoto);
//
//        mCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("dataMovie", getListMovie().get(position));
//                context.startActivity(intent);
//            }
//        });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(merchant);
                }
            });
        }
    }
}
