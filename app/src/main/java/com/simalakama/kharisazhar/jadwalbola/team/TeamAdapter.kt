package com.simalakama.kharisazhar.jadwalbola.team

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.model.Teams
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class TeamAdapter(private val teams: List<Teams>, private val listener: (Teams) -> Unit) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams(width = dip(50), height = dip(50))

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                }
            }
        }
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(teams: Teams, listener: (Teams) -> Unit) {
//            Picasso.get().load(teams.teamBadge).into(teamBadge)
            Glide.with(itemView.context).load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName

            itemView.onClick {
                listener(teams)
            }
        }
    }
}