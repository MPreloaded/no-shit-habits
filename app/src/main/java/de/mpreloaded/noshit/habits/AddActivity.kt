package de.mpreloaded.noshit.habits

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
    }

    fun addNewHabit(view: View) {
        val textName : EditText = findViewById(R.id.editTextHabitName)
        val habitName: String = textName.getText().toString()

        if (TextUtils.isEmpty(habitName)) {
            textName.setError("Input required")
        } else {

        }
    }
}