package com.simalakama.kharisazhar.jadwalbola.matches.tabs.last_match


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.matches.detail.DetailMatchActivity
import com.simalakama.kharisazhar.jadwalbola.matches.tabs.MatchAdapter
import com.simalakama.kharisazhar.jadwalbola.model.Match
import com.simalakama.kharisazhar.jadwalbola.utils.invisible
import com.simalakama.kharisazhar.jadwalbola.utils.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class LastMatchFragment : Fragment(), AnkoComponent<Context>, LastMatchView {

    private var matches: MutableList<Match> = mutableListOf()
    private lateinit var presenter: LastMatchPresenter
    private lateinit var adapter: MatchAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val ids: String = "4328"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = MatchAdapter(matches) {
            context?.startActivity<DetailMatchActivity>(
                "id" to "${it.idEvent}",
                "id_home_team" to "${it.idHomeTeam}",
                "id_away_team" to "${it.idAwayTeam}"
            )
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = LastMatchPresenter(this, request, gson)
        presenter.getLastMatch(ids)

        swipeRefresh.onRefresh {
            presenter.getLastMatch(ids)
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Last Match"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
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

                    listTeam = recyclerView {
                        id = R.id.list_match_last
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Match>) {
        swipeRefresh.isRefreshing = false
        matches.clear()
        matches.addAll(data)
        adapter.notifyDataSetChanged()
    }

}

