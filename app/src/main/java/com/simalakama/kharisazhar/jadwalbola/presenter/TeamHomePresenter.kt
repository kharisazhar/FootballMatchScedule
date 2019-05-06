package com.simalakama.kharisazhar.jadwalbola.presenter

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import com.simalakama.kharisazhar.jadwalbola.model.TeamsResponse
import com.simalakama.kharisazhar.jadwalbola.view.TeamHomeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamHomePresenter(
    private val view: TeamHomeView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamHome(idTeam: String?) {
        EspressoIdlingResource.increment()
        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()
                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLookUpTeam(idTeam)).await(),
                    TeamsResponse::class.java
                )
                view.showTeamHome(data!!.teams)
            }
        }
    }
}