package com.kplayout2019.screen.mainscreen.repository

import com.kplayout2019.model.AdsReceived
import com.kplayout2019.model.ThemeItem
import com.kplayout2019.packageNameApp
import com.kplayout2019.retrofit.RetrofitService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryWS {

    fun getAdsConfig(): Observable<AdsReceived> {

        return RetrofitService().getInstance().interfaces.getAdsConfig(packageNameApp,"regular")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getThemeConfig(): Observable<List<ThemeItem>> {
        return RetrofitService().getInstance().interfaces.getThemeConfig("com.keyboard.plus.theme.big")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


}