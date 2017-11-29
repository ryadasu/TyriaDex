package com.example.luke.tyriadex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.WalletResult;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 28/11/17.
 */

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private List<WalletResult> mDataSource;

    public WalletAdapter() {
        //
    }

    public WalletAdapter(List<WalletResult> wallet) {
        mDataSource = wallet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wallet, parent, false);
        return new ViewHolder(itemView);
    }

    public List<WalletResult> getmDataSource() {
        return mDataSource;
    }

    public void setmDataSource(List<WalletResult> mDataSource) {
        this.mDataSource = mDataSource;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mDataSource == null) {
            return 0;
        }
        else {
            return mDataSource.size();

        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WalletResult wallet = mDataSource.get(position);
        Picasso.with(holder.getContext())
                .load(wallet.getCurrency().getIcon())
                .placeholder(R.drawable.currency_placeholder)
                .fit()
                .into(holder.walletIcon);
        holder.walletName.setText(wallet.getCurrency().getName());

        int currencyQuantity = wallet.getValue();
        String currencyReadable = "";

        if (wallet.getId() == 1) {
            currencyReadable = String.format(Locale.ENGLISH,"%05d", currencyQuantity);
            currencyReadable = new StringBuilder(currencyReadable).insert(currencyReadable.length() - 4, "g ").toString();
            currencyReadable = new StringBuilder(currencyReadable).insert(currencyReadable.length() - 2, "s ").toString();
            currencyReadable = new StringBuilder(currencyReadable).insert(currencyReadable.length(), "c").toString();
        }
        else {
            currencyReadable = String.format("%,d", currencyQuantity);
        }

        holder.walletQuantity.setText(currencyReadable);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView walletQuantity, walletName;
        public ImageView walletIcon;
        Context context;

        public ViewHolder(View view) {
            super(view);

            walletQuantity = view.findViewById(R.id.tv_wallet_list_quantity);
            context = view.getContext();
            walletIcon = view.findViewById(R.id.iv_wallet_list_icon);
            walletName = view.findViewById(R.id.tv_wallet_list_name);
        }

        public Context getContext() {
            return context;
        }
    }
}
