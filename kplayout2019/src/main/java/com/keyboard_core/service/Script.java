package com.keyboard_core.service;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.inputmethod.latin.NgramContext;

import static com.android.inputmethod.latin.LatinIME.mDictionaryFacilitator;


public class Script {

    public Script(Context activity) {
        mDictionaryFacilitator.clearUserHistoryDictionary(activity);
        Log.d("testare_comp","start : "+mDictionaryFacilitator.isActive());
        NgramContext.WordInfo null_first = new NgramContext.WordInfo("",true);
        NgramContext.WordInfo[] mP = { null_first, new NgramContext.WordInfo(null),new NgramContext.WordInfo(null)};
        NgramContext ngramContext = new NgramContext(3,mP);

        for (int i = 0 ; i<5;i++) {
        mDictionaryFacilitator.addToUserHistory("mult", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multumesc", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multianual", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multinationala", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multiplicabil", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multiplicat", false, ngramContext, 1504777919, true); }

        for (int i = 0 ; i<5;i++) {
            mDictionaryFacilitator.addToUserHistory("multiplicativ", false, ngramContext, 1504777919, true); }

//        for (int i = 0 ; i<2;i++) {
//        addComposeWords("Bine","multumesc"); }
        Log.d("testare_comp","stop : "+mDictionaryFacilitator.isActive());
    }

    private void addComposeWords(String firstWord,String secondWord) {
        NgramContext.WordInfo primul, doilea, treilea;
        NgramContext.WordInfo[] grupare;
        NgramContext container;



        for (int i=0;i<2;i++) {

            primul = new NgramContext.WordInfo("Ss", false);
            doilea = new NgramContext.WordInfo("", true);
            treilea = new NgramContext.WordInfo(null);

            grupare = new NgramContext.WordInfo[]{primul, doilea, treilea};
            container = new NgramContext(3, grupare);

            mDictionaryFacilitator.addToUserHistory(firstWord, false, container, 1504777919, true);

            primul = new NgramContext.WordInfo(firstWord, false);
            doilea = new NgramContext.WordInfo("Ss", false);
            treilea =new NgramContext.WordInfo("", true);

            grupare = new NgramContext.WordInfo[]{primul, doilea, treilea};
            container = new NgramContext(3, grupare);

            mDictionaryFacilitator.addToUserHistory(secondWord, false, container, 1504777919, true);
        }
    }
}
