package com.simalakama.kharisazhar.jadwalbola.matches.tabs.last_match

import com.simalakama.kharisazhar.jadwalbola.model.Match

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Match>)
}