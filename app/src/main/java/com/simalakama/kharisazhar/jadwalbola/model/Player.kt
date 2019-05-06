package com.simalakama.kharisazhar.jadwalbola.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strTeam")
    var playerTeam: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strThumb")
    var playerImage: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDescription: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null
) : Parcelable