package com.simalakama.kharisazhar.jadwalbola.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.R.id.search_match
import com.simalakama.kharisazhar.jadwalbola.R.layout.fragment_match
import com.simalakama.kharisazhar.jadwalbola.adapter.ViewPagerAdapter
import com.simalakama.kharisazhar.jadwalbola.matches.search.MatchSearchActivity
import com.simalakama.kharisazhar.jadwalbola.matches.tabs.next_match.MatchListFragment
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.support.v4.find

class MatchFragment : Fragment() {

    private var id: String = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViewPager(matches_viewpager)
        matches_tabs.setupWithViewPager(matches_viewpager)
//        loadAdBanner()
    }

//    private fun loadAdBanner(){
//        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
//        MobileAds.initialize(context, getString(R.string.app_id))
//        val adRequest = AdRequest.Builder().build()
//        adViewNextMach.loadAd(adRequest)
//    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(fragment_match, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = find<Toolbar>(R.id.matches_toolbar)
        toolbar.inflateMenu(R.menu.menu_search)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                search_match -> {
                    val intent = Intent(context, MatchSearchActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(MatchListFragment.newFragment(1, id), "NEXT MATCH")
        adapter.addFrag(MatchListFragment.newFragment(2, id), "LAST MATCH")
        viewPager.adapter = adapter
    }
}



