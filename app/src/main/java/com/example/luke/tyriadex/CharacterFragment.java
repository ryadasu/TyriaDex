package com.example.luke.tyriadex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.magnet.android.mms.exception.SchemaException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by luke on 22/11/17.
 */

public class CharacterFragment extends Fragment {

//    OnDataPass dataPasser;
    String apiKey = null;
//
//    public interface OnDataPass {
//        void onDataPass(String data);
//    }
//
//    public void passData(String data) {
//        dataPasser.onDataPass(data);
//    }

    public CharacterFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_character, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Character Fragment load api key: " + apiKey);

        ApiCall.update(rootView.getContext(), apiKey);

        TextView tv = rootView.findViewById(R.id.tv_character);
        String characterNames = "API call failed";
        try {
            characterNames = ApiCall.getCharacterNames(apiKey);
        } catch (SchemaException e) {
            e.printStackTrace();
        }
        tv.setText(characterNames);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        dataPasser = (OnDataPass) context;
    }

}
