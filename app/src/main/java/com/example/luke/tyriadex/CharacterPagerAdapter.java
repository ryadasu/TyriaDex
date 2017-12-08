package com.example.luke.tyriadex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.luke.tyriadex.model.beans.AllCharactersResult;

import java.util.List;

/**
 * Created by luke on 29/11/17.
 */

public class CharacterPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    List<AllCharactersResult> characterDetails;

    public CharacterPagerAdapter(FragmentManager fm, int NumOfTabs, List<AllCharactersResult> characterDetails) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.characterDetails = characterDetails;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                CharacterDetailsFragment charZero = new CharacterDetailsFragment();
                charZero.setCharDetails(characterDetails.get(0));
                return charZero;
            case 1:
                CharacterDetailsFragment charOne = new CharacterDetailsFragment();
                charOne.setCharDetails(characterDetails.get(1));
                return charOne;
            case 2:
                CharacterDetailsFragment charTwo = new CharacterDetailsFragment();
                charTwo.setCharDetails(characterDetails.get(2));
                return charTwo;
            case 3:
                CharacterDetailsFragment charThree = new CharacterDetailsFragment();
                charThree.setCharDetails(characterDetails.get(3));
                return charThree;
            case 4:
                CharacterDetailsFragment charFour = new CharacterDetailsFragment();
                charFour.setCharDetails(characterDetails.get(4));
                return charFour;
            case 5:
                CharacterDetailsFragment charFive = new CharacterDetailsFragment();
                charFive.setCharDetails(characterDetails.get(5));
                return charFive;
            case 6:
                CharacterDetailsFragment charSix = new CharacterDetailsFragment();
                charSix.setCharDetails(characterDetails.get(6));
                return charSix;
            case 7:
                CharacterDetailsFragment charSeven = new CharacterDetailsFragment();
                charSeven.setCharDetails(characterDetails.get(7));
                return charSeven;
            case 8:
                CharacterDetailsFragment charEight = new CharacterDetailsFragment();
                charEight.setCharDetails(characterDetails.get(8));
                return charEight;
            case 9:
                CharacterDetailsFragment charNine = new CharacterDetailsFragment();
                charNine.setCharDetails(characterDetails.get(9));
                return charNine;
            case 10:
                CharacterDetailsFragment charTen = new CharacterDetailsFragment();
                charTen.setCharDetails(characterDetails.get(10));
                return charTen;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}