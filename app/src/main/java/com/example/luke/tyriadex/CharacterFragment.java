package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
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
import java.util.stream.Collectors;

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


    private class CharacterDetailsAsyncCall extends AsyncTask<String, Void, String> {

        TextView tvD;

        public CharacterDetailsAsyncCall(TextView tvDetails) {
            tvD = tvDetails;
        }

        public String doInBackground(String... params) {
            String result = null;
            try {
                result = ApiCall.getCharacterByName(params[0], params[1]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void onPostExecute(String result) {
            tvD.setText(result);
        }
    }

    private class CharacterNamesAsyncCall extends AsyncTask<String, Void, List<String>> {

        TextView tvN, tvD;

        public CharacterNamesAsyncCall(TextView tvNames, TextView tvDetails) {
            tvN = tvNames;
            tvD = tvDetails;
        }

        public List<String> doInBackground(String... params) {
            List<String> result = null;
            try {
                result = ApiCall.getCharacterNames(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }

            if (result != null) {
                String s = result.get(1);
                CharacterDetailsAsyncCall async = new CharacterDetailsAsyncCall(tvD);
                Log.d("LOG", s);
                async.execute(s, apiKey);
            }

            return result;
        }

        public void onPostExecute(List<String> result) {
            String text = "";

            for (String s: result) {
                text += (s + ", ");
            }
            tvN.setText(text.substring(0, text.length() - 2));
        }
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

        ApiCall.update(getContext());

        TextView tvNames = rootView.findViewById(R.id.tv_characters);
        TextView tvDetails = rootView.findViewById(R.id.tv_char_detail);
        CharacterNamesAsyncCall async = new CharacterNamesAsyncCall(tvNames, tvDetails);
        async.execute(apiKey);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        dataPasser = (OnDataPass) context;
    }

}
