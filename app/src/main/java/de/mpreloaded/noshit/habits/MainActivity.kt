package de.mpreloaded.noshit.habits

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.mpreloaded.noshit.habits.db.HabitsDatabaseHelper

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: HabitsDatabaseHelper
    lateinit var recyclerView : RecyclerView
    lateinit var addButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = HabitsDatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        addButton = findViewById(R.id.floatingActionButton)
    }

    fun createNewHabit(view: View) {
        val intent = Intent(this, AddActivity::class.java).apply {

        }
        startActivity(intent)
    }
}