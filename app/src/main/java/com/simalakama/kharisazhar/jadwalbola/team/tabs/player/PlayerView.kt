package com.simalakama.kharisazhar.jadwalbola.team.tabs.player

import com.simalakama.kharisazhar.jadwalbola.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayer(data: List<Player>)
}