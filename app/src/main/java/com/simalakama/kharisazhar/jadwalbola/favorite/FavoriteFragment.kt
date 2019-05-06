package com.simalakama.kharisazhar.jadwalbola.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.R.layout.fragment_favorite
import com.simalakama.kharisazhar.jadwalbola.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(favorites_viewpager)
        favorites_tabs.setupWithViewPager(favorites_viewpager)
//        loadAdBanner()
    }

//    private fun loadAdBanner() {
//        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
//        MobileAds.initialize(context, getString(R.string.app_id))
//        val adRequest = AdRequest.Builder().build()
////        adViewFavMatch.loadAd(adRequest)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(fragment_favorite, container, false)
        return view
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(FavoriteMatchFragment(), "MATCHES")
        adapter.addFrag(FavoriteTeamFragment(), "TEAMS")
        viewPager.adapter = adapter
    }
}
