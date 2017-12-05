package com.example.luke.tyriadex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Item;
import com.example.luke.tyriadex.model.beans.TradingResult;
import com.example.luke.tyriadex.model.beans.WalletResult;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 28/11/17.
 */

public class TradingAdapter extends RecyclerView.Adapter<TradingAdapter.ViewHolder> {
    private List<TradingResult> mDataSource;

    public TradingAdapter() {
        //
    }

    public TradingAdapter(List<TradingResult> items) {
        mDataSource = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trading, parent, false);
        return new ViewHolder(itemView);
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
        TradingResult result = mDataSource.get(position);
        Picasso.with(holder.getContext())
                .load(result.getItem().getIcon())
                .placeholder(R.drawable.placeholder)
                .fit()
                .into(holder.itemIcon);

        int rarity;

        switch (result.getItem().getRarity()) {
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

        holder.itemIcon.setBackgroundResource(rarity);

        String itemQuantityAndName = "";
        itemQuantityAndName += (String.valueOf(result.getQuantity() + "x " + result.getItem().getName()));

        holder.itemName.setText(itemQuantityAndName);

        int pickupCoins = result.getPrice();
        String pickupCoinsReadable = String.format(Locale.ENGLISH,"%05d", pickupCoins);
        pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 4, "g ").toString();
        pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 2, "s ").toString();
        pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length(), "c").toString();

        holder.itemCost.setText(pickupCoinsReadable);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemCost, itemName;
        public ImageView itemIcon;
        Context context;

        public ViewHolder(View view) {
            super(view);

            itemCost = view.findViewById(R.id.tv_trading_list_cost);
            context = view.getContext();
            itemIcon = view.findViewById(R.id.iv_trading_list_icon);
            itemName = view.findViewById(R.id.tv_trading_list_name);
        }

        public Context getContext() {
            return context;
        }
    }

    public List<TradingResult> getmDataSource() {
        return mDataSource;
    }

    public void setmDataSource(List<TradingResult> mDataSource) {
        this.mDataSource = mDataSource;
    }
}
