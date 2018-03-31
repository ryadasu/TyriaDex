package com.example.luke.tyriadex;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.AllCharactersResult;
import com.example.luke.tyriadex.model.beans.Equipment;
import com.example.luke.tyriadex.model.beans.ItemByIdResult;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by luke on 8/12/17.
 */

public class CharacterDetailsFragment extends Fragment {

    String apiKey = null;
    AllCharactersResult charDetails = null;
    Button btCharInfoSwap = null;
    EquipmentAsyncCall async;
    ProgressBar loading;
    List<Equipment> equipmentWithItems;
    RecyclerView equipmentAndInventoryRecycler;
    Boolean isEquipMode = true;
    CharacterDetailsEquipmentAdapter equipmentAdapter;
    //also inventory adapter

    public CharacterDetailsFragment() {
        //
    }

    private class EquipmentAsyncCall extends AsyncTask<List<Equipment>, Void, List<Equipment>> {


        public EquipmentAsyncCall() {
            //
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Equipment> doInBackground(List<Equipment>... params) {
            List<Equipment> result = null;
            try {
                List<Equipment> equips = params[0];
                for (Equipment e : equips) {
                    String id = e.getId().toString();
                    e.setItem(ApiCall.getItemObjectById(id));
                }

                for (Equipment e : equips) {
                    if (e.getUpgrades() != null) {
                        for (int upgradeId : e.getUpgrades()) {
                            String upgradeIdString = Integer.toString(upgradeId);
                            ItemByIdResult r = ApiCall.getItemObjectById(upgradeIdString);
                            e.addUpgradeItem(r);
                        }
                    }
                }

                result = equips;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<Equipment> result) {
            if (result != null) {
                equipmentWithItems = result;
            }
            else {
                Log.e("LOG", "Equipment with items result null");
            }
            loading.setVisibility(View.INVISIBLE);
            equipmentAdapter.setmDataSource(result);
            equipmentAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_character_details, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        ApiCall.update(getContext());

        Button swapButton = rootView.findViewById(R.id.bt_character_info_swap);
        swapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btCharInfoSwap.getText() == "Swap to Inventory") { //if mode is currently set to equipment
                    btCharInfoSwap.setText(R.string.char_swap_equip);
                    //swap mode to inventory
                    isEquipMode = false;
                }
                else if (btCharInfoSwap.getText() == "Swap to Equipment") { //if mode is currently set to inventory
                    btCharInfoSwap.setText(R.string.char_swap_inv);
                    //swap mode to equipment
                    isEquipMode = true;
                }
                else {
                    Log.e("LOG", "Swap button text not correct");
                }
            }
        });

        loading = rootView.findViewById(R.id.pb_character_details_loading);

        TextView tvCharLevel = rootView.findViewById(R.id.tv_character_level);
        ImageView ivCharProfession = rootView.findViewById(R.id.iv_character_profession);
        ImageView ivCharRace = rootView.findViewById(R.id.iv_character_race);
        TextView tvCharGuild = rootView.findViewById(R.id.tv_character_guild);

        tvCharLevel.setText(charDetails.getLevel().toString());
//        tvCharGuild.setText(charDetails.getGuild());

        switch(charDetails.getProfession()) {
            case "Elementalist":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.elementalist)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Engineer":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.engineer)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Guardian":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.guardian)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Mesmer":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.mesmer)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Necromancer":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.necromancer)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Ranger":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.ranger)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Revenant":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.revenant)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Thief":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.thief)
                        .fit()
                        .into(ivCharProfession);
                break;
            case "Warrior":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.warrior)
                        .fit()
                        .into(ivCharProfession);
                break;
        }

        switch(charDetails.getRace()) {
            case "Human":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.human)
                        .fit()
                        .into(ivCharRace);
                break;
            case "Charr":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.charr)
                        .fit()
                        .into(ivCharRace);
                break;
            case "Asura":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.asura)
                        .fit()
                        .into(ivCharRace);
                break;
            case "Norn":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.norn)
                        .fit()
                        .into(ivCharRace);
                break;
            case "Sylvari":
                Picasso.with(rootView.getContext())
                        .load(R.drawable.sylvari)
                        .fit()
                        .into(ivCharRace);
                break;
        }

        if (isEquipMode) {
            equipmentAndInventoryRecycler = rootView.findViewById(R.id.recycler_character_details);
            equipmentAdapter = new CharacterDetailsEquipmentAdapter();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            equipmentAndInventoryRecycler.setHasFixedSize(true);
            equipmentAndInventoryRecycler.setLayoutManager(mLayoutManager);
            equipmentAndInventoryRecycler.setItemAnimator(new DefaultItemAnimator());
            equipmentAndInventoryRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            equipmentAndInventoryRecycler.setAdapter(equipmentAdapter);

            async = new EquipmentAsyncCall();
            async.execute(charDetails.getEquipment());
        }
        else {
            //inventory mode
        }


        return rootView;
    }

    public AllCharactersResult getCharDetails() {
        return charDetails;
    }

    public void setCharDetails(AllCharactersResult charDetails) {
        this.charDetails = charDetails;
    }
}
