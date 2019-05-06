package com.simalakama.kharisazhar.jadwalbola.presenter

import com.google.gson.Gson
import com.simalakama.kharisazhar.jadwalbola.CoroutineContextProviderTest
import com.simalakama.kharisazhar.jadwalbola.api.ApiRepository
import com.simalakama.kharisazhar.jadwalbola.api.TheSportDBApi
import com.simalakama.kharisazhar.jadwalbola.matches.detail.MatchDetailPresenter
import com.simalakama.kharisazhar.jadwalbola.model.Match
import com.simalakama.kharisazhar.jadwalbola.model.MatchResponse
import com.simalakama.kharisazhar.jadwalbola.matches.detail.MatchDetailView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    private lateinit var presenter: MatchDetailPresenter
    private val contex: CoroutineContextProviderTest = CoroutineContextProviderTest()

    @Mock
    private
    lateinit var view: MatchDetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, CoroutineContextProviderTest())
    }

    @Test
    fun getEventDetail() {
        val matchs: MutableList<Match> = mutableListOf()
        val match: MutableList<Match> = mutableListOf()
        val response = MatchResponse(matchs,match)
        val id = "441613"

        GlobalScope.launch(contex.main) {
            Mockito.`when`(
                gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getEventDetail(id)).await(),
                    MatchResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatchDetail(match)
            Mockito.verify(view).hideLoading()
        }
    }
}