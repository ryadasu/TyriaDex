package com.example.luke.tyriadex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.AllCharactersResult;
import com.squareup.picasso.Picasso;

/**
 * Created by luke on 8/12/17.
 */

public class CharacterDetailsFragment extends Fragment {

    String apiKey = null;
    AllCharactersResult charDetails = null;

    public CharacterDetailsFragment() {
        //
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

        TextView tvCharProfession = rootView.findViewById(R.id.tv_character_profession);
        ImageView ivCharProfession = rootView.findViewById(R.id.iv_character_profession);
        tvCharProfession.setText(charDetails.getProfession());

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

        return rootView;
    }

    public AllCharactersResult getCharDetails() {
        return charDetails;
    }

    public void setCharDetails(AllCharactersResult charDetails) {
        this.charDetails = charDetails;
    }
}