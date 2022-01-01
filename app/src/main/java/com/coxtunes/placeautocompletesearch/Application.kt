package com.coxtunes.placeautocompletesearch

import android.app.Application
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient

/**
 * @Author: Nayeem Shiddiki Abir
 * @Date : 01-Jan-22
 * @Project: PlaceAutoCompleteSearch
 */
class Application : Application(){

    lateinit var placesClient: PlacesClient

    override fun onCreate() {
        super.onCreate()

        // Initialize Place Api With Key for Hole Application Access
        val apiKey = getString(R.string.api_key);
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey);
        }
    }
}