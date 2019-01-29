package com.kplayout2019.model

class ThemeItem {
    var id =0
    var store_link = ""
    var name = ""
    var account = ""
    var primary_color = ""
    var accent_color = ""
    var price = ""
    var price_category = ""
    var icon = ""
    var facebook_interstitial_id = ""
    var thumbnail = ""
    var no_ads = ""
    var likes = ""
    var screenshots:List<String> = ArrayList()
    var account_internal_ads:MutableList<AdsItem> = ArrayList()
    var account_wallpapers:MutableList<AdsItem> = arrayListOf()

}
