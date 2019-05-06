package com.simalakama.kharisazhar.jadwalbola.team.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.R.drawable.ic_add_to_favorites
import com.simalakama.kharisazhar.jadwalbola.R.drawable.ic_added_to_favorites
import com.simalakama.kharisazhar.jadwalbola.adapter.ViewPagerAdapter
import com.simalakama.kharisazhar.jadwalbola.db.TeamObject
import com.simalakama.kharisazhar.jadwalbola.db.database
import com.simalakama.kharisazhar.jadwalbola.model.Teams
import com.simalakama.kharisazhar.jadwalbola.team.tabs.OverviewFragment
import com.simalakama.kharisazhar.jadwalbola.team.tabs.player.PlayerFragment
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity() {

    private lateinit var team: Teams
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        supportActionBar?.hide()

        val toolbar = team_detail_toolbar
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        toolbar.inflateMenu(R.menu.detail_menu)
        menuItem = toolbar.menu

        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.setOnMenuItemClickListener {
            if (it.itemId.equals(R.id.add_to_favorite)) {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                return@setOnMenuItemClickListener true
            } else {
                onOptionsItemSelected(it)
            }
        }

        team = intent.getParcelableExtra("TEAM_PARCEL")
        getDetail()

        setupViewPager(team_detail_viewpager)
        team_detail_tabs.setupWithViewPager(team_detail_viewpager)

        favoriteState()
        setFavorite()
//        loadAdBanner()
    }

    private fun getDetail() {
        Glide.with(this).load(team.teamBadge).into(team_badge)
        team_name.text = team.teamName
        team_year.text = team.teamFormedYear
        team_stadium.text = team.teamStadium
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(OverviewFragment.newFragment(team), "Overview")
        adapter.addFrag(PlayerFragment.newFragment(team.teamId!!), "Players")
        viewPager.adapter = adapter
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    TeamObject.TABLE_TEAM,
                    TeamObject.TEAM_ID to team.teamId,
                    TeamObject.TEAM_BADGE to team.teamBadge,
                    TeamObject.TEAM_NAME to team.teamName,
                    TeamObject.TEAM_STADIUM to team.teamStadium,
                    TeamObject.TEAM_YEAR to team.teamFormedYear,
                    TeamObject.TEAM_DESC to team.teamDescription
                )
            }
            team_detail_viewpager.snackbar("Added to favorite").show()
        } catch (e: SQLiteConstraintException) {
            Toast.makeText(applicationContext, "Error" + e, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(TeamObject.TABLE_TEAM, "(TEAM_ID = {id})", "id" to team.teamId!!)
            }
        } catch (e: SQLiteConstraintException) {
            team_detail_viewpager.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamObject.TABLE_TEAM)
                .whereArgs("(TEAM_ID = {id})", "id" to team.teamId!!)
            val favorite = result.parseList(classParser<TeamObject>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

//    private fun loadAdBanner() {
//        MobileAds.initialize(this, getString(R.string.app_id))
//        val adRequest = AdRequest.Builder().build()
//        adViewDetailTeam.loadAd(adRequest)
//    }
}
