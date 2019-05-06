package com.simalakama.kharisazhar.jadwalbola.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.R.id.*
import com.simalakama.kharisazhar.jadwalbola.favorite.FavoriteFragment
import com.simalakama.kharisazhar.jadwalbola.fragment.MatchFragment
import com.simalakama.kharisazhar.jadwalbola.team.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RewardedVideoAdListener {

    private lateinit var mAdView: AdView
    private lateinit var mRewardedVideoAd: RewardedVideoAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadAdBanner()
        loadRewardedAd()

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                matchs -> {
                    loadMatchFragment(savedInstanceState)
//                    if (mRewardedVideoAd.isLoaded()) {
//                        mRewardedVideoAd.show()
//                    }
                }
                team_nav_bottom -> {
                    loadTeamFragment(savedInstanceState)
                    if (mRewardedVideoAd.isLoaded()) {
                        mRewardedVideoAd.show()
                    }
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
//                    if (mRewardedVideoAd.isLoaded()) {
//                        mRewardedVideoAd.show()
//                    }
                }
            }
            true
        }
        bottom_navigation.selectedItemId = matchs
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadRewardedAd() {
        MobileAds.initialize(this, getString(R.string.app_id))
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.rewardedVideoAdListener = this
        mRewardedVideoAd.loadAd(
                getString(R.string.ad_unit_id_rewarded),
                AdRequest.Builder().build()
        )
    }

    private fun loadAdBanner() {
        mAdView = findViewById(R.id.adView)
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, getString(R.string.app_id))
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    override fun onRewardedVideoAdClosed() {
        Log.d("TAG_AD","An Ad Close")
    }

    override fun onRewardedVideoAdLeftApplication() {
        Log.d("TAG_AD","An Ad LeftApp")
    }

    override fun onRewardedVideoAdLoaded() {
        Log.d("TAG_AD","An Ad VIdeo Load")
    }

    override fun onRewardedVideoAdOpened() {
//        Toast.makeText(this, "An Ad Opened", Toast.LENGTH_LONG).show()
        Log.d("TAG_AD","An Ad OPEN")
    }

    override fun onRewardedVideoCompleted() {
//        Toast.makeText(this, "An Ad Complete", Toast.LENGTH_LONG).show()
        Log.d("TAG_AD","An Ad Complete")
    }

    override fun onRewarded(reward: RewardItem?) {
//        Toast.makeText(this, "Wow you get ${reward?.type} ${reward?.amount} ", Toast.LENGTH_SHORT)
//                .show()
        Log.d("TAG_AD","Wow you get ${reward?.type} ${reward?.amount}")
    }

    override fun onRewardedVideoStarted() {
//        Toast.makeText(this, "An Ad Started", Toast.LENGTH_LONG).show()
        Log.d("TAG_AD","An Ad started")
    }

    override fun onRewardedVideoAdFailedToLoad(error: Int) {
        /*Toast.makeText(this, "An Ad Failed", Toast.LENGTH_LONG).show()*/
        Log.d("TAG_AD","An Ad FAILED ${error}")
    }
}
