package com.tyga.fintech.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tyga.fintech.R;
import com.tyga.fintech.model.Transaksi;
import com.tyga.fintech.util.FormatTanggal;

import java.util.List;

public class HistoryNasabahAdapter extends RecyclerView.Adapter<HistoryNasabahAdapter.CategoryViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Transaksi transaksi);
    }

    private Context context;
    private OnItemClickListener listener;
    private List<Transaksi> listTransaksi;

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_history_nasabah, viewGroup, false);
        return new CategoryViewHolder(itemRow);
    }

    public HistoryNasabahAdapter(Context context, OnItemClickListener itemClickListener) {
        this.context = context;
        this.listener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        categoryViewHolder.bind(getListTransaksi().get(position), listener);
    }

    @Override
    public int getItemCount() {
        return getListTransaksi().size();
    }

    public List<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<Transaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView nama, tanggal, jam, namaLpd, nominal;
        CardView mCard;
        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mCard = itemView.findViewById(R.id.cdTop);
            nama = itemView.findViewById(R.id.merchantHistoryNasabah);
            tanggal = itemView.findViewById(R.id.tanggalHistoryNasabah);
            jam = itemView.findViewById(R.id.jamHistoryNasabah);
            namaLpd = itemView.findViewById(R.id.lpdHistoryNasabah);
            nominal = itemView.findViewById(R.id.hargaHistoryNasabah);
        }

        public void bind(final Transaksi transaksi, final HistoryNasabahAdapter.OnItemClickListener listener) {
            nama.setText(transaksi.getNama());
            tanggal.setText(FormatTanggal.formatTanggal(transaksi.getTanggal()));
            jam.setText(FormatTanggal.formatJam(transaksi.getTanggal()));
            namaLpd.setText("");
            nominal.setText(String.valueOf(transaksi.getNominal()));

            listener.onItemClick(transaksi);
        }
    }
}