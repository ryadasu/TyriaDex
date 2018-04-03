package com.example.luke.tyriadex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Equipment;
import com.example.luke.tyriadex.model.beans.Item;
import com.example.luke.tyriadex.model.beans.TradingResult;
import com.example.luke.tyriadex.model.beans.WalletResult;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 28/11/17.
 */

public class CharacterDetailsEquipmentAdapter extends RecyclerView.Adapter<CharacterDetailsEquipmentAdapter.ViewHolder> {
    private List<Equipment> mDataSource;

    public CharacterDetailsEquipmentAdapter() {
        //
    }

    public CharacterDetailsEquipmentAdapter(List<Equipment> items) {
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
        Equipment result = mDataSource.get(position);
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

        if (result.getItem().getLevel() != null) {
            holder.itemLevel.setText(result.getItem().getLevel().toString());
        }
        holder.itemName.setText(result.getItem().getName());

        String itemDetails = "";
        if (result.getUpgradeItems().size() > 0) {
            itemDetails += result.getUpgradeItems().get(0).getName();
        }
        holder.itemSubtitle.setText(itemDetails);

        String itemDetailsTwo = "";
        if (result.getUpgradeItems().size() > 1) {
            itemDetailsTwo += result.getUpgradeItems().get(1).getName();
        }
        holder.itemSubtitleTwo.setText(itemDetailsTwo);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView itemSubtitle, itemName, itemLevel, itemSubtitleTwo;
        public ImageView itemIcon;
        Context context;

        public ViewHolder(View view) {
            super(view);

            context = view.getContext();
            itemSubtitle = view.findViewById(R.id.tv_trading_list_subtitle_one);
            itemSubtitleTwo = view.findViewById(R.id.tv_trading_list_subtitle_two);
            itemIcon = view.findViewById(R.id.iv_trading_list_icon);
            itemName = view.findViewById(R.id.tv_trading_list_title);
            itemLevel = view.findViewById(R.id.tv_trading_list_quantity);
        }

        public Context getContext() {
            return context;
        }
    }

    public List<Equipment> getmDataSource() {
        return mDataSource;
    }

    public void setmDataSource(List<Equipment> mDataSource) {
        this.mDataSource = mDataSource;
    }
}
