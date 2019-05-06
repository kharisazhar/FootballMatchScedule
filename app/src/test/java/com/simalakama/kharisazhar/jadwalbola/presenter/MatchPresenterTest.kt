package com.simalakama.kharisazhar.jadwalbola.presenter

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.CoroutineContextProviderTest
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.matches.tabs.next_match.MatchPresenter
import com.simalakama.kharisazhar.jadwalbola.matches.tabs.next_match.MatchView
import com.simalakama.kharisazhar.jadwalbola.model.Match
import com.simalakama.kharisazhar.jadwalbola.model.MatchResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchPresenterTest {

    private lateinit var presenter: MatchPresenter
    private val contex: CoroutineContextProviderTest = CoroutineContextProviderTest()

    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(
                view,
                apiRepository,
                gson,
                1,
                CoroutineContextProviderTest()
        )
    }

    @Test
    fun getMatchList() {
        val matchs: MutableList<Match> = mutableListOf()
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matchs,match)
        val id = "441613"

        GlobalScope.launch(contex.main) {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getMatch(id)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getMatchList(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showTeamList(matchs)
            Mockito.verify(view).hideLoading()
        }
    }
}