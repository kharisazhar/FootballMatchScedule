package com.simalakama.kharisazhar.jadwalbola.matches.tabs.next_match

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import com.simalakama.kharisazhar.jadwalbola.model.LeagueResponse
import com.simalakama.kharisazhar.jadwalbola.model.MatchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val fixture: Int = 1,
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

    fun getMatchList(id: String?) {

        EspressoIdlingResource.increment()

        view.showLoading()

        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val api = if (fixture == 1) TheSportDBApi.getMatch(id)
                else TheSportDBApi.getLastMatch(id)

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(api).await(),
                    MatchResponse::class.java
                )

                view.showTeamList(data.events)
                view.hideLoading()
            }
        }
    }
}