package ru.perelyginva.todolist_v01.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.perelyginva.todolist_v01.util.MIGRATION_1_2
import ru.perelyginva.todolist_v01.util.MIGRATION_2_3

@Database(entities = arrayOf(Todo::class), version = 3)

abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TodoDatabase::class.java,
                "tododb")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3) //переход на новую ДБ
                .build()

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

    }

}