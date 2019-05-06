package com.simalakama.kharisazhar.jadwalbola.team.tabs.player

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import com.simalakama.kharisazhar.jadwalbola.idling.EspressoIdlingResource
import com.simalakama.kharisazhar.jadwalbola.model.PlayerResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getPlayer(id: String?) {
        EspressoIdlingResource.increment()

        view.showLoading()

        GlobalScope.launch(context.main) {
            if (!EspressoIdlingResource.idlingResource.isIdleNow()) {
                EspressoIdlingResource.decrement()

                val data = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getPlayer(id)).await(),
                    PlayerResponse::class.java
                )



                view.showPlayer(data!!.player)
                view.hideLoading()

            }
        }
    }


}
