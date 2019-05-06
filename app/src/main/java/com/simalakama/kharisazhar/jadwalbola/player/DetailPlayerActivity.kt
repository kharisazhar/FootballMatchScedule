package com.simalakama.kharisazhar.jadwalbola.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.model.Player
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        player = intent.getParcelableExtra("PLAYER_PARCEL")
        passData()
    }

    private fun passData() {
        Glide.with(this).load(player.playerImage).into(img_detail_player)
        tv_detail_player_name.text = player.playerName
        tv_detail_player_team.text = player.playerTeam
        tv_detail_player_position.text = player.playerPosition
        tv_detail_player_desc.text = player.playerDescription
    }

}
