package com.simalakama.kharisazhar.jadwalbola.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 15) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(
            Favorite.TABLE_FAVORITE, true,
            Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            Favorite.EVENT_ID to TEXT,
            Favorite.EVENT_DATE to TEXT,
            Favorite.EVENT_TIME to TEXT,
            Favorite.EVENT_NAME to TEXT,
            Favorite.HOME_SCORE to TEXT,
            Favorite.AWAY_SCORE to TEXT,
            Favorite.NAME_HOME to TEXT,
            Favorite.NAME_AWAY to TEXT,
            Favorite.HOME_TEAM_ID to TEXT,
            Favorite.AWAY_TEAM_ID to TEXT,
            Favorite.HOME_GOALS_DETAIL to TEXT,
            Favorite.AWAY_GOALS_DETAIL to TEXT,
            Favorite.HOME_SHOTS to INTEGER,
            Favorite.AWAY_SHOTS to INTEGER,
            Favorite.BADGE_HOME to TEXT,
            Favorite.BADGE_AWAY to TEXT,
            Favorite.HOME_GOAL_KEEPER to TEXT,
            Favorite.HOME_DEFENCE to TEXT,
            Favorite.HOME_MILDFIELD to TEXT,
            Favorite.AWAY_GOAL_KEEPER to TEXT,
            Favorite.AWAY_DEFENCE to TEXT,
            Favorite.AWAY_MILDFIELD to TEXT
        )

        db.createTable(
            TeamObject.TABLE_TEAM, true,
            TeamObject.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamObject.TEAM_ID to TEXT,
            TeamObject.TEAM_BADGE to TEXT,
            TeamObject.TEAM_NAME to TEXT,
            TeamObject.TEAM_STADIUM to TEXT,
            TeamObject.TEAM_YEAR to TEXT,
            TeamObject.TEAM_DESC to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
        db.dropTable(TeamObject.TABLE_TEAM, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)