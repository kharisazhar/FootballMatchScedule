package com.simalakama.kharisazhar.jadwalbola.presenter

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.CoroutineContextProviderTest
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.model.Teams
import com.simalakama.kharisazhar.jadwalbola.model.TeamsResponse
import com.simalakama.kharisazhar.jadwalbola.view.TeamHomeView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamHomePresenterTest {

    private lateinit var presenter: TeamHomePresenter
    private val contex: CoroutineContextProviderTest = CoroutineContextProviderTest()

    @Mock
    private
    lateinit var view: TeamHomeView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamHomePresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getTeamHome() {
        val teams: MutableList<Teams> = mutableListOf()
        val response = TeamsResponse(teams)
        val id = "134301"

        GlobalScope.launch(contex.main) {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getLookUpTeam(id)).await(),
                    TeamsResponse::class.java
                )
            ).thenReturn(response)

            presenter.getTeamHome(id)

            Mockito.verify(view).showTeamHome(teams)
        }
    }
}