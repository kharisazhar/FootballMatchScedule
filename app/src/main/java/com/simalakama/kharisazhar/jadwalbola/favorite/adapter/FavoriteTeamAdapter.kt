package com.simalakama.kharisazhar.jadwalbola.favorite.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.db.TeamObject
import com.simalakama.kharisazhar.jadwalbola.team.TeamAdapter
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick

class FavoriteTeamAdapter(private val teams: List<TeamObject>, private val listener: (TeamObject) -> Unit) :
    RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TeamAdapter.TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = teams.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(team: TeamObject, listener: (TeamObject) -> Unit) {
//            Picasso.get().load(team.teamBadge).into(teamBadge)
            Glide.with(itemView.context).load(team.teamBadge).into(teamBadge)
            teamName.text = team.teamName

            itemView.onClick { listener(team) }
        }
    }
}