package com.simalakama.kharisazhar.jadwalbola.matches.tabs.last_match

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import com.simalakama.kharisazhar.jadwalbola.model.MatchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LastMatchPresenter(
    private val view: LastMatchFragment,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getLastMatch(ids: String?) {
        EspressoIdlingResource.increment()
        view.showLoading()
        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLastMatch(ids)).await(),
                    MatchResponse::class.java
                )

                view.showTeamList(data.events)
                view.hideLoading()
            }
        }
    }
}