package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.tyriadex.model.beans.AllCharactersResult;
import com.magnet.android.mms.exception.SchemaException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by luke on 22/11/17.
 */

public class CharacterFragment extends Fragment {

    String apiKey = null;
    AllCharactersAsyncCall async;
    ProgressBar loading;
    TabLayout tabLayout;
    ViewPager viewPager;
    CharacterPagerAdapter adapter;
    View rootView;
    Bundle args;
    List<AllCharactersResult> characterDetails;

    public CharacterFragment() {
        //
    }

    private class AllCharactersAsyncCall extends AsyncTask<String, Void, List<AllCharactersResult>> {


        public AllCharactersAsyncCall() {
            //
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<AllCharactersResult> doInBackground(String... params) {
            List<AllCharactersResult> result = null;
            try {
                result = ApiCall.getAllCharacters(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(List<AllCharactersResult> result) {
            if (result != null) {
                characterDetails = result;

                CharacterNamesAsyncCall charNameAsync = new CharacterNamesAsyncCall();
                charNameAsync.execute(apiKey);
            }
        }
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

        Boolean cancelled;

        public CharacterNamesAsyncCall() {
            //
        }

        @Override
        protected void onPreExecute() {
            cancelled = false;

            super.onPreExecute();
        }

        public List<String> doInBackground(String... params) {
            List<String> result = new ArrayList<String>();
            try {
                result = ApiCall.getCharacterNames(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onCancelled() {
            cancelled = true;
            super.onCancelled();
        }

        public void onPostExecute(List<String> result) {
            if (!cancelled && result != null) {
                loading.setVisibility(View.INVISIBLE);
                for (String name : result) {
                    tabLayout.addTab(tabLayout.newTab().setText(name));
                }

                viewPager = rootView.findViewById(R.id.pager);
                adapter = new CharacterPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), characterDetails);
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
                tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int index = tab.getPosition();
                        viewPager.setCurrentItem(index);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

            }
            else {
                Log.e("LOG", "Character names call result is null");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_character, container, false);
        args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Character Fragment load api key: " + apiKey);

        ApiCall.update(getContext());
        ((MainActivity) getActivity()).setToolbarTitle("Characters");

        loading = rootView.findViewById(R.id.pb_character_loading);
        tabLayout = rootView.findViewById(R.id.tab_layout);

        async = new AllCharactersAsyncCall();
        async.execute(apiKey);


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        dataPasser = (OnDataPass) context;
    }

    @Override
    public void onDetach() {
        if (async != null) {
            async.cancel(true);
        }

        super.onDetach();
    }

}
