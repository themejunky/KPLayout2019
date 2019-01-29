package com.kplayout2019.retrofit

import com.kplayout2019.model.AdsReceived
import com.kplayout2019.model.ThemeItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/ads")
    fun getAdsConfig(@Query("package_name") packageName: String,@Query("type") type: String): Observable<AdsReceived>

    @GET("/themes")
    fun getThemeConfig(@Query("package_name") packageName: String): Observable<List<ThemeItem>>

}