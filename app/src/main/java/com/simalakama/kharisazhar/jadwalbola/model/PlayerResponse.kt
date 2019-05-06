package com.simalakama.kharisazhar.jadwalbola.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
    @SerializedName("player") val player: List<Player>
)