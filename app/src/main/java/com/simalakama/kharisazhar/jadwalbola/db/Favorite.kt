package com.simalakama.kharisazhar.jadwalbola.db

data class Favorite(
    val id: Long?,
    val idEvent: String?,
    val dateEvent: String?,
    val dateTime: String?,
    val nameEvent: String?,
    val homeScore: String?,
    val awayScore: String?,
    val nameHome: String?,
    val nameAway: String?,
    val homeTeamId: String?,
    val awayTeamId: String?,
    val homeGoalsDetail: String?,
    val awayGoalsDetail: String?,
    val homeShots: Int?,
    val awayShots: Int?,
    val badgeHome: String?,
    val badgeAway: String?,
    val homeGoalKeeper: String?,
    val homeDefence: String?,
    val homeMildField: String?,
    val awayGoalKeeper: String?,
    val awayDefence: String?,
    val awayMildField: String?
) {

    companion object {
        //match
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val EVENT_NAME: String = "EVENT_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val NAME_HOME: String = "NAME_HOME"
        const val HOME_TEAM_ID: String = "HOME_TEAM_ID"
        const val AWAY_TEAM_ID: String = "AWAY_TEAM_ID"
        const val NAME_AWAY: String = "NAME_AWAY"
        const val HOME_GOALS_DETAIL: String = "HOME_GOALS_DETAIL"
        const val AWAY_GOALS_DETAIL: String = "AWAY_GOALS_DETAIL"
        const val HOME_SHOTS: String = "HOME_SHOTS"
        const val AWAY_SHOTS: String = "AWAY_SHOTS"
        const val BADGE_HOME: String = "BADGE_HOME"
        const val BADGE_AWAY: String = "BADGE_AWAY"
        const val HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val HOME_DEFENCE: String = "HOME_DEFENCE"
        const val HOME_MILDFIELD: String = "HOME_MILDFIELD"
        const val AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val AWAY_DEFENCE: String = "AWAY_DEFENCE"
        const val AWAY_MILDFIELD: String = "AWAY_MILDFIELD"
    }
}