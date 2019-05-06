package com.simalakama.kharisazhar.jadwalbola.matches.search

import com.simalakama.kharisazhar.jadwalbola.model.Match

interface MatchSearchView {
    fun showLoading()
    fun hideLoading()
    fun showList(data: List<Match>)
}