package com.simalakama.kharisazhar.jadwalbola.matches.search

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.LinearLayout
import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.matches.detail.DetailMatchActivity
import com.simalakama.kharisazhar.jadwalbola.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MatchSearchActivity : AppCompatActivity(), MatchSearchView {
    private var events: MutableList<Match> = mutableListOf()
    private lateinit var searchView: SearchView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var matchesList: RecyclerView
    private lateinit var presenter: MatchSearchPresenter
    private lateinit var adapter: MatchSearchAdapter
    private var query: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchSearchPresenter(this, request, gson)

        adapter = MatchSearchAdapter(events) {
            startActivity<DetailMatchActivity>(
                "id" to "${it.idEvent}",
                "id_home_team" to "${it.idHomeTeam}",
                "id_away_team" to "${it.idAwayTeam}"
            )
        }

        linearLayout {
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.darker_gray,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    matchesList = recyclerView {
                        id = R.id.rv_match_list
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(applicationContext)
                    }
                }
            }
        }

        matchesList.adapter = adapter

        swipeRefresh.onRefresh {
            presenter.getSearchEvent(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        searchView.requestFocusFromTouch()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                presenter.getSearchEvent(text)
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return false
            }

        })

        return true
    }


    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }


    override fun showList(data: List<Match>) {
        hideLoading()
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
