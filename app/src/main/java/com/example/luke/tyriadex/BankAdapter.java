package com.example.luke.tyriadex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.BankResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luke on 28/11/17.
 */

public class BankAdapter extends RecyclerView.Adapter<BankAdapter.ViewHolder> {

    private List<BankResult> mDataSource;

    public BankAdapter() {
        //
    }

    public BankAdapter(List<BankResult> bank) {
        mDataSource = bank;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wallet, parent, false);
        return new ViewHolder(itemView);
    }

    public List<BankResult> getmDataSource() {
        return mDataSource;
    }

    public void setmDataSource(List<BankResult> mDataSource) {
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
        BankResult bank = mDataSource.get(position);

        if (bank != null) {


            int rarity;

            if (bank.getId() != 0) {
                Picasso.with(holder.getContext())
                        .load(bank.getItem().getIcon())
                        .placeholder(R.drawable.placeholder)
                        .fit()
                        .into(holder.bankIcon);

                switch (bank.getItem().getRarity()) {
                    case("Junk"):
                        rarity = R.drawable.rarity_junk;
                        break;
                    case("Basic"):
                        rarity=R.drawable.rarity_basic;
                        break;
                    case("Fine"):
                        rarity = R.drawable.rarity_fine;
                        break;
                    case("Masterwork"):
                        rarity = R.drawable.rarity_masterwork;
                        break;
                    case("Rare"):
                        rarity = R.drawable.rarity_rare;
                        break;
                    case("Exotic"):
                        rarity = R.drawable.rarity_exotic;
                        break;
                    case("Ascended"):
                        rarity = R.drawable.rarity_ascended;
                        break;
                    case("Legendary"):
                        rarity = R.drawable.rarity_legendary;
                        break;
                    default:
                        rarity = R.drawable.rarity_basic;
                        break;
                }

                holder.bankIcon.setBackgroundResource(rarity);
            }
            else {
                Picasso.with(holder.getContext())
                        .load(R.drawable.placeholder)
                        .fit()
                        .into(holder.bankIcon);

            }

            String quantityAndName = "";

            if (bank.getId() == 0) {
                quantityAndName = "EMPTY";
            }
            else {
                quantityAndName += (String.valueOf(bank.getCount()) + "x " + bank.getItem().getName());

            }
            holder.bankName.setText(quantityAndName);
        }
        else {
            Log.d("LOG", "Null bank result at" + String.valueOf(position));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bankName;
        public ImageView bankIcon;
        Context context;

        public ViewHolder(View view) {
            super(view);

            context = view.getContext();
            bankIcon = view.findViewById(R.id.iv_wallet_list_icon);
            bankName = view.findViewById(R.id.tv_wallet_list_name);
        }

        public Context getContext() {
            return context;
        }
    }
}
