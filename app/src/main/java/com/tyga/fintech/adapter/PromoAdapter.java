package com.tyga.fintech.adapter;

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
import com.bumptech.glide.request.RequestOptions;
import com.tyga.fintech.R;
import com.tyga.fintech.model.Promo;

import java.util.List;

public class PromoAdapter extends RecyclerView.Adapter<PromoAdapter.CategoryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Promo promo);
    }

    private Context context;
    private OnItemClickListener listener;
    private List<Promo> listPromo;
    private String status, category;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_promo_home, viewGroup, false);
        return new CategoryViewHolder(itemRow);

    }

    public PromoAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.listener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        categoryViewHolder.bind(getListPromo().get(position), listener);
    }



    @Override
    public int getItemCount() {
        return getListPromo().size();
    }

    public List<Promo> getListPromo() {
        return listPromo;
    }

    public void setListPromo(List<Promo> listPromo) {
        this.listPromo = listPromo;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView judul,tanggal,nominal;
        CardView mCard;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.iv_icon_promo);
            judul = itemView.findViewById(R.id.tv_judul_promo);
            tanggal = itemView.findViewById(R.id.tv_tanggal_promo);
            nominal = itemView.findViewById(R.id.tv_nominal_promo);
        }

        public void bind(final Promo promo, final OnItemClickListener listener) {
            judul.setText(promo.getNama_promo());
            Glide.with(context)
                .load(promo.getImage_promo())
                .apply(new RequestOptions())
                .placeholder(R.drawable.placeholder)
                .into(imgPhoto);

            nominal.setText(String.valueOf(promo.getNominal()));
            tanggal.setText(promo.getTanggal_berakhir());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(promo);
                }
            });
        }
    }
}
