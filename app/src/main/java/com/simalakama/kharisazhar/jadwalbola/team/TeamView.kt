package com.simalakama.kharisazhar.jadwalbola.team

import com.simalakama.kharisazhar.jadwalbola.model.League
import com.simalakama.kharisazhar.jadwalbola.model.Teams

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showLeague(data: List<League>)
    fun showTeamList(data: List<Teams>)
}