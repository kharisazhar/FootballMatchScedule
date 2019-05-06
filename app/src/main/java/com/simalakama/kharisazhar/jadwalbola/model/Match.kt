package com.simalakama.kharisazhar.jadwalbola.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Match(

    @SerializedName("idEvent")
    @Expose
    var idEvent: String? = null,

    @SerializedName("strEvent")
    @Expose
    var eventName: String? = null,

    @SerializedName("dateEvent")
    @Expose
    var eventDate: String? = null,

    @SerializedName("strTime")
    var eventTime: String? = null,

    @SerializedName("idHomeTeam")
    @Expose
    var idHomeTeam: String? = null,

    @SerializedName("idAwayTeam")
    @Expose
    var idAwayTeam: String? = null,

    @SerializedName("strHomeTeam")
    @Expose
    var homeTeamName: String? = null,

    @SerializedName("strAwayTeam")
    @Expose
    var awayTeamName: String? = null,

    @SerializedName("intHomeScore")
    @Expose
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    @Expose
    var awayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    @Expose
    var homeGoalsDetail: String? = null,

    @SerializedName("strAwayGoalDetails")
    @Expose
    var awayGoalsDetail: String? = null,

    @SerializedName("intHomeShots")
    @Expose
    var homeShots: String? = null,

    @SerializedName("intAwayShots")
    @Expose
    var awayShots: String? = null,

    @SerializedName("strSport")
    var sportName: String? = null,

    //lineup

    @SerializedName("strHomeLineupGoalkeeper")
    var homeLineupGoalKeeper: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeLineupDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeLineupMidfield: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayLineupGoalKeeper: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayLineupDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayLineupMidfield: String? = null

) : Parcelable