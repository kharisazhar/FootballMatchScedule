package com.simalakama.kharisazhar.jadwalbola.team

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import com.simalakama.kharisazhar.jadwalbola.model.LeagueResponse
import com.simalakama.kharisazhar.jadwalbola.model.TeamsResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getLeague() {
        EspressoIdlingResource.increment()

        view.showLoading()

        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLeague()).await(),
                    LeagueResponse::class.java
                )

                view.showLeague(data.countrys)
                view.hideLoading()
            }
        }
    }

    fun getTeamList(league: String?) {

        EspressoIdlingResource.increment()

        view.showLoading()

        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getAllTeams(league)).await(),
                    TeamsResponse::class.java
                )

                view.showTeamList(data!!.teams)
                view.hideLoading()
            }
        }
    }

    fun getTeamSearch(query: String?) {

        EspressoIdlingResource.increment()

        view.showLoading()

        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamSearch(query)).await(),
                    TeamsResponse::class.java
                )

                view.showTeamList(data.teams)
                view.hideLoading()
            }
        }
    }
}