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
        val sdkConfiguration = SdkConfiguration.Builder("b03e6dccfe9e4abab02470a39c88d5dc").build()
        MoPub.initializeSdk(this, sdkConfiguration, null)

        //AppMonet initialization.
        val appMonetConfiguration = AppMonetConfiguration.Builder().applicationId("3zeuyua").build()
        AppMonet.init(this, appMonetConfiguration)
    }
}