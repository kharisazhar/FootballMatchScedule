package com.simalakama.kharisazhar.jadwalbola.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Teams(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strStadium")
    var teamStadium: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: String? = null,

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null
) : Parcelable