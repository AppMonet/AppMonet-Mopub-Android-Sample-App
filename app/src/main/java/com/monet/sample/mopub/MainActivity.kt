package com.monet.sample.mopub

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    /**
     * Clean up
     */
    override fun onDestroy() {
        moPubView.destroy()
        interstitial.destroy()
        super.onDestroy()
    }

    /**
     * Sets up MoPub Interstitial.
     */
    private fun setupMoPubInterstitial() {
        interstitial = MoPubInterstitial(this, "a49430ee57ee4401a9eda6098726ce54")
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
                interstitial?.destroy()
            }

            override fun onInterstitialDismissed(interstitial: MoPubInterstitial?) {
                showToast("Interstitial Dismissed")
                interstitial?.destroy()
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
        moPubView.adUnitId = "b03e6dccfe9e4abab02470a39c88d5dc"
        moPubView.bannerAdListener = object : MoPubView.BannerAdListener {
            override fun onBannerExpanded(banner: MoPubView?) {
            }

            override fun onBannerLoaded(banner: MoPubView) {
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
     * Listener on mrect button that will trigger AppMonet's addBids method.
     */
    private fun loadMrectAd() {
        loadMrect.setOnClickListener {
            AppMonet.addBids(moPubView, 1500) { moPubView ->
                moPubView.loadAd()
            }
        }
    }

    /**
     * Listener on load interstitial button that will trigger load on MoPubInterstitial.
     */
    private fun loadInterstitial() {
        loadInterstitial.setOnClickListener {
            setupMoPubInterstitial()
            interstitial.load()
        }
    }

    /**
     * Listener on show interstitial button that will trigger show on MoPubInterstitial.
     */
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
