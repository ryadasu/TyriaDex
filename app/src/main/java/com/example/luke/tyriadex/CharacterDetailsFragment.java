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
import com.example.luke.tyriadex.model.beans.Bag;
import com.example.luke.tyriadex.model.beans.Equipment;
import com.example.luke.tyriadex.model.beans.Inventory;
import com.example.luke.tyriadex.model.beans.ItemByIdResult;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luke on 8/12/17.
 */

public class CharacterDetailsFragment extends Fragment {

    String apiKey = null;
    AllCharactersResult charDetails = null;
    EquipmentAsyncCall equipAsync;
    InventoryAsyncCall invAsync;
    ProgressBar loading;
    List<Equipment> equipmentWithItems;
    List<ItemByIdResult> inventory;
    RecyclerView equipmentAndInventoryRecycler;
    Boolean isEquipMode = true;
    CharacterDetailsEquipmentAdapter equipmentAdapter;
    CharacterDetailsInventoryAdapter inventoryAdapter;

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

    private class InventoryAsyncCall extends AsyncTask<List<Bag>, Void, List<ItemByIdResult>> {


        public InventoryAsyncCall() {
            //
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<ItemByIdResult> doInBackground(List<Bag>... params) {
            List<ItemByIdResult> result = new ArrayList<ItemByIdResult>();
            try {
                List<Bag> bags = params[0];
                for (Bag b : bags) {
                    for (Inventory i : b.getInventory()) {
                        if (i != null) {
                            String itemId = i.getId().toString();
                            ItemByIdResult r = ApiCall.getItemObjectById(itemId);
                            r.setCount(i.getCount());
                            result.add(r);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<ItemByIdResult> result) {
            if (result != null) {
                inventory = result;
            }
            else {
                Log.e("LOG", "Equipment with items result null");
            }
            loading.setVisibility(View.INVISIBLE);
            inventoryAdapter.setmDataSource(result);
            inventoryAdapter.notifyDataSetChanged();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_character_details, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        ApiCall.update(getContext());
        ((MainActivity) getActivity()).setToolbarTitle("Characters");

        final Button swapButton = rootView.findViewById(R.id.bt_character_info_swap);
        swapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isEquipMode) { //if mode is currently set to equipment
                    swapButton.setText(R.string.char_swap_equip);
                    //swap mode to inventory
                    isEquipMode = false;
                    update(rootView);
                }
                else { //if mode is currently set to inventory
                    swapButton.setText(R.string.char_swap_inv);
                    //swap mode to equipment
                    isEquipMode = true;
                    update(rootView);
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

        equipmentAndInventoryRecycler = rootView.findViewById(R.id.recycler_character_details);
        equipmentAdapter = new CharacterDetailsEquipmentAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        equipmentAndInventoryRecycler.setHasFixedSize(true);
        equipmentAndInventoryRecycler.setLayoutManager(mLayoutManager);
        equipmentAndInventoryRecycler.setItemAnimator(new DefaultItemAnimator());
        equipmentAndInventoryRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        equipmentAndInventoryRecycler.setAdapter(equipmentAdapter);

        equipAsync = new EquipmentAsyncCall();
        equipAsync.execute(charDetails.getEquipment());


        return rootView;
    }

    public void update(View v) {
        if (isEquipMode) {
            equipmentAndInventoryRecycler = v.findViewById(R.id.recycler_character_details);
            equipmentAdapter = new CharacterDetailsEquipmentAdapter();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            equipmentAndInventoryRecycler.setHasFixedSize(true);
            equipmentAndInventoryRecycler.setLayoutManager(mLayoutManager);
            equipmentAndInventoryRecycler.setItemAnimator(new DefaultItemAnimator());
            equipmentAndInventoryRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            equipmentAndInventoryRecycler.setAdapter(equipmentAdapter);

            equipAsync = new EquipmentAsyncCall();
            equipAsync.execute(charDetails.getEquipment());
        }
        else {
            equipmentAndInventoryRecycler = v.findViewById(R.id.recycler_character_details);
            inventoryAdapter = new CharacterDetailsInventoryAdapter();
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            equipmentAndInventoryRecycler.setHasFixedSize(true);
            equipmentAndInventoryRecycler.setLayoutManager(mLayoutManager);
            equipmentAndInventoryRecycler.setItemAnimator(new DefaultItemAnimator());
            equipmentAndInventoryRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            equipmentAndInventoryRecycler.setAdapter(inventoryAdapter);

            invAsync = new InventoryAsyncCall();
            invAsync.execute(charDetails.getBags());
        }
    }

    public AllCharactersResult getCharDetails() {
        return charDetails;
    }

    public void setCharDetails(AllCharactersResult charDetails) {
        this.charDetails = charDetails;
    }
}
