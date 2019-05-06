package com.simalakama.kharisazhar.jadwalbola.matches.detail

import com.simalakama.kharisazhar.jadwalbola.model.Match


interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<Match>)
}
