package de.mpreloaded.noshit.habits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.mpreloaded.noshit.habits.db.HabitsDatabaseHelper

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: HabitsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = HabitsDatabaseHelper(this)
    }

    fun addHabit(v: View) {
    }
}