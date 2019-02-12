package com.kplayout2019.screen.moreapps.repository

import android.util.Log
import com.kplayout2019.model.ThemeItem
import com.kplayout2019.packageNameApp
import com.kplayout2019.retrofit.RetrofitService
import com.kplayout2019.screen.moreapps.MoreAppsViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

class RepositoryMoreApps(val mViewModel: MoreAppsViewModel) {

    private fun getThemeConfig(): Observable<List<ThemeItem>> {
        return RetrofitService().getInstance().interfaces.getThemeConfig("com.keyboard.plus.theme.big")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getMoreApps(): Disposable {
        return getThemeConfig().subscribe(::successeMoreApps, ::erroreMoreApps)
    }
    private fun successeMoreApps(mResult: List<ThemeItem>){
        Log.d("testRetrofit","successeWallpaper")
        with (mViewModel){
            isInternet.value = true
            moreAppsList.clear()
            for (mData in mResult[0].account_internal_ads){
                if(mData.text.contains("type_promo",true)){
                    moreAppsList.add(mData)
                    triggerMoreApps.value = true
                }
            }
        }


    }
    private fun erroreMoreApps(mError: Throwable){
        Log.d("testRetrofit","successeWallpaper " + mError.message)
        Log.d("testRetrofit","successeWallpaper " + mError.stackTrace)
        if (mError is IOException) {
            mViewModel.isInternet.value = false
        }
    }
}