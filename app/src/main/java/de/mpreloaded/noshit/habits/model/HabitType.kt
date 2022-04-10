package de.mpreloaded.noshit.habits.model

enum class HabitType(val dbString: String) {
    BOOLEAN("boolean"),
    INTEGER("int"),
    TIMESPAN("timeperiod"),
    TIMESTAMP("timestamp")
}