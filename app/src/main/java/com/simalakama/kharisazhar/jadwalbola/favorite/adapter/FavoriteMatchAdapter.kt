package com.simalakama.kharisazhar.jadwalbola.favorite.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.simalakama.kharisazhar.jadwalbola.R
import com.simalakama.kharisazhar.jadwalbola.db.Favorite
import com.simalakama.kharisazhar.jadwalbola.utils.changeFormatDate
import com.simalakama.kharisazhar.jadwalbola.utils.strTodate
import com.simalakama.kharisazhar.jadwalbola.utils.toGMTFormat
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.text.SimpleDateFormat

class FavoriteMatchAdapter(private val matches: List<Favorite>, private val listener: (Favorite) -> Unit) :
    RecyclerView.Adapter<FavoriteMatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMatchViewHolder {
        return FavoriteMatchViewHolder(
            MatchFavUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: FavoriteMatchViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }
}

class MatchFavUI : AnkoComponent<ViewGroup> {
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

                        textView {
                            id = R.id.date_event
                            textSize = 16f
                            this.gravity = Gravity.CENTER_HORIZONTAL
                        }

                        textView {
                            id = R.id.time_event
                            textSize = 16f
                            this.gravity = Gravity.CENTER_HORIZONTAL
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

class FavoriteMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventName: TextView = view.find(R.id.last_event_name)
    private val eventDate: TextView = view.find(R.id.date_event)
    private val eventTime: TextView = view.find(R.id.time_event)

    @SuppressLint("SimpleDateFormat")
    fun bindItem(teams: Favorite, listener: (Favorite) -> Unit) {

        val date = strTodate(teams.dateEvent)
        val dateTime = toGMTFormat(teams.dateEvent, teams.dateTime)

        eventName.text = teams.nameEvent
        eventDate.text = changeFormatDate(date)
        eventTime.text = SimpleDateFormat("HH:mm").format(dateTime)
        itemView.setOnClickListener { listener(teams) }
    }
}