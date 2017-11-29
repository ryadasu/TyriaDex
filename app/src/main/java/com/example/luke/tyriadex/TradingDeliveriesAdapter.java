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
import com.example.luke.tyriadex.model.beans.WalletResult;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 28/11/17.
 */

public class TradingDeliveriesAdapter extends RecyclerView.Adapter<TradingDeliveriesAdapter.ViewHolder> {
    private List<Item> mDataSource;

    public TradingDeliveriesAdapter(List<Item> items) {
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
            return 10;
        }
        else {
            return mDataSource.size();
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = mDataSource.get(position);
        Picasso.with(holder.getContext())
                .load(item.getItemByIdResult().getIcon())
                .placeholder(R.drawable.currency_placeholder)
                .fit()
                .into(holder.itemIcon);

        String itemQuantityAndName = "";
        itemQuantityAndName += (String.valueOf(item.getCount()) + "x " + item.getItemByIdResult().getName());

        holder.itemName.setText(itemQuantityAndName);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemCost, itemName;
        public ImageView itemIcon;
        Context context;

        public ViewHolder(View view) {
            super(view);

//            itemCost = view.findViewById(R.id.tv_trading_list_cost);
            context = view.getContext();
            itemIcon = view.findViewById(R.id.iv_trading_list_icon);
            itemName = view.findViewById(R.id.tv_trading_list_name);
        }

        public Context getContext() {
            return context;
        }
    }
}
