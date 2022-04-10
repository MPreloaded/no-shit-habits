package de.mpreloaded.noshit.habits.db

import android.provider.BaseColumns

object DBContract {

    class HabitEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "habits"
            val COLUMN_HABITID = "id"
            val COLUMN_NAME = "name"
            val COLUMN_TYPE = "type"
        }
    }
}