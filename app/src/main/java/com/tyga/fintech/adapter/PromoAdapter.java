package com.tyga.fintech.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
//        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_promo, viewGroup, false);
//        return new CategoryViewHolder(itemRow);
        return null;
    }

    public PromoAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.listener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        categoryViewHolder.bind(getListPromo().get(position), listener);
//

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
        TextView judul;
        CardView mCard;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
//            mCard = itemView.findViewById(R.id.favorite_card);
        }

        public void bind(final Promo promo, final OnItemClickListener listener) {
//            judul.setText(promo.getNama());
//        Glide.with(context)
//                .load("https://image.tmdb.org/t/p/w185/"+getListPromo().get(position).getPoster_path())
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
                    listener.onItemClick(promo);
                }
            });
        }
    }
}
