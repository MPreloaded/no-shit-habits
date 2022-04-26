package de.mpreloaded.noshit.habits.model

enum class HabitType(val dbString: String) {
    DATE("date"),
    INTEGER("int"),
    TIMESPAN("timeperiod"),
    TIMESTAMP("timestamp")
}