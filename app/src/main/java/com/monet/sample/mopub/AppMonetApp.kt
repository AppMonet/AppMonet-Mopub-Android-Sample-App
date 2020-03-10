package com.monet.sample.mopub

import android.app.Application
import com.monet.bidder.AppMonet
import com.monet.bidder.AppMonetConfiguration
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration

class AppMonetApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //MoPub initialization
        val sdkConfiguration = SdkConfiguration.Builder("").build()
        MoPub.initializeSdk(this, sdkConfiguration, null)

        //AppMonet initialization.
        val appMonetConfiguration = AppMonetConfiguration.Builder().applicationId("ADD_UNIT").build()
        AppMonet.init(this, appMonetConfiguration)
    }
}