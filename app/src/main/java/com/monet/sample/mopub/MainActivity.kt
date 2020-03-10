package com.monet.sample.mopub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.monet.bidder.AppMonet
import com.mopub.mobileads.MoPubErrorCode
import com.mopub.mobileads.MoPubInterstitial
import com.mopub.mobileads.MoPubView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var interstitial: MoPubInterstitial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupMoPubMrect()
        setupMoPubInterstitial()
        loadMrectAd()
        loadInterstitial()
        showInterstitial()
    }

    override fun onDestroy() {
        moPubView.destroy()
        interstitial.destroy()
        super.onDestroy()
    }

    /**
     * Sets up MoPub Interstitial.
     */
    private fun setupMoPubInterstitial() {
        interstitial = MoPubInterstitial(this, "ADUNIT")
        interstitial.interstitialAdListener = object : MoPubInterstitial.InterstitialAdListener {
            override fun onInterstitialLoaded(interstitial: MoPubInterstitial?) {
                showToast("Interstitial Loaded")
            }

            override fun onInterstitialShown(interstitial: MoPubInterstitial?) {
                showToast("Interstitial Shown")
            }

            override fun onInterstitialFailed(
                interstitial: MoPubInterstitial?,
                errorCode: MoPubErrorCode?
            ) {
                showToast("Interstitial Failed")
            }

            override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
                showToast("Interstitial Dismissed")
            }

            override fun onInterstitialClicked(interstitial: MoPubInterstitial?) {
                showToast("Interstitial Clicked")
            }
        }
    }

    /**
     * Sets up MoPubView.
     */
    private fun setupMoPubMrect() {
        moPubView.bannerAdListener = object : MoPubView.BannerAdListener {
            override fun onBannerExpanded(banner: MoPubView?) {
            }

            override fun onBannerLoaded(banner: MoPubView?) {
                showToast("Banner Ad Loaded")
            }

            override fun onBannerCollapsed(banner: MoPubView?) {
            }

            override fun onBannerFailed(banner: MoPubView?, errorCode: MoPubErrorCode?) {
                showToast("Banner Failed to Load")
            }

            override fun onBannerClicked(banner: MoPubView?) {
                showToast("Banner Clicked")
            }
        }
    }

    /**
     * Implementation on mrect button that will trigger AppMonet's addBids method.
     */
    private fun loadMrectAd() {
        loadMrect.setOnClickListener {
            AppMonet.addBids(moPubView, 2000) { moPubView ->
                moPubView.loadAd()
            }
        }
    }

    private fun loadInterstitial() {
        loadInterstitial.setOnClickListener {
            interstitial.load()
        }
    }

    private fun showInterstitial() {
        showInterstitial.setOnClickListener {
            if (interstitial.isReady) {
                interstitial.show()
            } else {
                showToast("Interstitial Is Not Ready")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
