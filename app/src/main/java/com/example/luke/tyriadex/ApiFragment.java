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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.magnet.android.mms.exception.SchemaException;

/**
 * Created by luke on 22/11/17.
 */

public class ApiFragment extends Fragment {

    String apiKey = null;
    OnDataPass dataPasser;

    //Key 1:     76EE69D4-505D-3F4A-A0B5-D2EA178A57A8A93C4FC6-40DC-4564-9D6F-AA3F4207E69A
    //Key 2:     83A97AA8-8754-6844-B926-EB80A9352E6E393ACCD0-BF88-41A5-8FCC-3514B167AC41

    private class AccountAsyncCall extends AsyncTask<String, Void, String> {

        TextView tvAccountName;

        public AccountAsyncCall(TextView tvCO) {
            tvAccountName = tvCO;
        }

        public String doInBackground(String... params) {
            String result = null;
            try {
                result = ApiCall.getAccount(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void onPostExecute(String result) {
            tvAccountName.setText(result);
        }
    }

    public ApiFragment() {
        //
    }

    public interface OnDataPass {
        void onDataPass(String data);
    }

    public void passData(String data) {
        dataPasser.onDataPass(data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_api, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "API Fragment load api key: " + apiKey);
        ((MainActivity) getActivity()).setToolbarTitle("TyriaDex: Api Key");

        ApiCall.update(getContext());


        final TextView accountName = rootView.findViewById(R.id.tv_api_account);


        final EditText editTextApiKey = rootView.findViewById(R.id.et_api_key);
        editTextApiKey.setText(apiKey);
        Button buttonSaveApiKey = rootView.findViewById(R.id.bt_submit_api);
        buttonSaveApiKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextApiKey.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter an API key", Toast.LENGTH_SHORT).show();
                }
                else {
                    apiKey = editTextApiKey.getText().toString();
                    passData(apiKey);
                    Toast.makeText(getContext(), "API key saved", Toast.LENGTH_SHORT).show();
                    accountName.setText("Loading...");
                    Log.d("LOG", "API Fragment set api key: " + apiKey);
                    AccountAsyncCall accountAsyncCall = new AccountAsyncCall(accountName);
                    accountAsyncCall.execute(apiKey);
                }
            }
        });
        Button buttonClearApiKey = rootView.findViewById(R.id.bt_clear_api);
        buttonClearApiKey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiKey = null;
                editTextApiKey.setText("");
                passData(apiKey);
                Log.d("LOG", "API Fragment clear api key: " + apiKey);
                accountName.setText("None");
            }
        });


        if (apiKey != null) {
            AccountAsyncCall accountAsyncCall = new AccountAsyncCall(accountName);
            accountAsyncCall.execute(apiKey);
        }

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass) context;
    }
}