package com.simalakama.kharisazhar.jadwalbola.db

data class TeamObject(
    val id: Long?,
    val teamId: String?,
    val teamBadge: String?,
    val teamName: String?,
    val teamStadium: String?,
    val teamYear: String?,
    val teamDesc: String?
) {
    companion object {
        //teams
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_YEAR: String = "TEAM_YEAR"
        const val TEAM_DESC: String = "TEAM_DESC"
    }
}