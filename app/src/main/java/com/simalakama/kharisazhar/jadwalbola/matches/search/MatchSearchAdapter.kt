package com.simalakama.kharisazhar.jadwalbola.matches.search

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.model.Match
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class MatchSearchAdapter(private val matches: List<Match>, private val listener: (Match) -> Unit) :
    RecyclerView.Adapter<LastMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        return LastMatchViewHolder(
            TeamLastMatchUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class TeamLastMatchUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent) {
                    bottomMargin = dip(8)
                }
                orientation = LinearLayout.VERTICAL

                cardView {
                    linearLayout {
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(16)
                        orientation = LinearLayout.VERTICAL

                        linearLayout {
                            lparams(width = matchParent, height = wrapContent)
                            orientation = LinearLayout.HORIZONTAL
                            gravity = Gravity.CENTER

                            textView {
                                id = R.id.home_score
                                rightPadding = dip(10)
                                textSize = 16f
                            }

                            textView {
                                id = R.id.away_score
                                leftPadding = dip(10)
                                textSize = 16f
                            }
                        }

                        textView {
                            id = R.id.last_event_name
                            text = context.getString(R.string.vs)
                            textSize = 18f
                            this.gravity = Gravity.CENTER_HORIZONTAL
                        }


                    }
                }
            }
        }
    }

}

class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventName: TextView = view.find(R.id.last_event_name)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayScore: TextView = view.find(R.id.away_score)

    fun bindItem(teams: Match, listener: (Match) -> Unit) {

        eventName.text = teams.eventName
        homeScore.text = teams.homeScore
        awayScore.text = teams.awayScore

        itemView.setOnClickListener { listener(teams) }
    }
}