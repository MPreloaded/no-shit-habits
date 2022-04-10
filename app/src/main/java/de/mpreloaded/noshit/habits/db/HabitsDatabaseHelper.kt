package de.mpreloaded.noshit.habits.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import de.mpreloaded.noshit.habits.model.Habit
import de.mpreloaded.noshit.habits.model.HabitType

class HabitsDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertHabit(habit: Habit): Habit {
        // Retrieve database to insert new entry
        val db = writableDatabase

        val values = ContentValues()
        values.put(DBContract.HabitEntry.COLUMN_NAME, habit.name)
        values.put(DBContract.HabitEntry.COLUMN_TYPE, habit.type.dbString)

        val newRowId = db.insert(DBContract.HabitEntry.TABLE_NAME, null, values)

        return Habit(newRowId, habit.name, habit.type)
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteHabit(id: Long): Boolean {
        // Retrieve database to delete entry
        val db = writableDatabase

        val selection = DBContract.HabitEntry.COLUMN_HABITID + " = ?"
        val selectionArgs = arrayOf(id.toString())

        db.delete(DBContract.HabitEntry.TABLE_NAME, selection, selectionArgs)

        return true
    }

    @SuppressLint("Range")
    fun getHabit(id: Long): ArrayList<Habit> {
        val habits = ArrayList<Habit>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.HabitEntry.TABLE_NAME + " WHERE " + DBContract.HabitEntry.COLUMN_HABITID + "='" + id.toString() + "'", null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var type: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(DBContract.HabitEntry.COLUMN_NAME))
                type = cursor.getString(cursor.getColumnIndex(DBContract.HabitEntry.COLUMN_TYPE))

                habits.add(Habit(id, name, HabitType.valueOf(type)))
                cursor.moveToNext()
            }
        }
        return habits
    }

    @SuppressLint("Range")
    fun getAllHabits(id: Long): ArrayList<Habit> {
        val habits = ArrayList<Habit>()
        val db = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + DBContract.HabitEntry.TABLE_NAME, null)
        } catch (e: SQLiteException) {
            // if table not yet present, create it
            db.execSQL(SQL_CREATE_ENTRIES)
            return ArrayList()
        }

        var name: String
        var type: String
        if (cursor!!.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                name = cursor.getString(cursor.getColumnIndex(DBContract.HabitEntry.COLUMN_NAME))
                type = cursor.getString(cursor.getColumnIndex(DBContract.HabitEntry.COLUMN_TYPE))

                habits.add(Habit(id, name, HabitType.valueOf(type)))
                cursor.moveToNext()
            }
        }
        return habits
    }

    companion object {
        val DATABASE_NAME = "NoShitHabits.db"
        // To be incremented on scheme update
        val DATABASE_VERSION = 1

        private val SQL_CREATE_ENTRIES = "CREATE TABLE " + DBContract.HabitEntry.TABLE_NAME + " (" +
                DBContract.HabitEntry.COLUMN_HABITID + " INT AUTO_INCREMENT," +
                DBContract.HabitEntry.COLUMN_NAME + " TEXT," +
                DBContract.HabitEntry.COLUMN_TYPE + " TEXT," +
                "PRIMARY KEY (" + DBContract.HabitEntry.COLUMN_HABITID + "))"

        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS" + DBContract.HabitEntry.TABLE_NAME
    }
}