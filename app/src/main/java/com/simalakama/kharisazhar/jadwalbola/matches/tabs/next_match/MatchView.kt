package com.simalakama.kharisazhar.jadwalbola.matches.tabs.next_match

import com.simalakama.kharisazhar.jadwalbola.model.League
import com.simalakama.kharisazhar.jadwalbola.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Match>)
    fun showLeague(data: List<League>)
}