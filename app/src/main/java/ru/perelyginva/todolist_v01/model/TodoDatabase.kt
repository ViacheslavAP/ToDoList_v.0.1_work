package ru.perelyginva.todolist_v01.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//TODO("измени версию базы, если что то добавил.You need сhange number db when add new element")
@Database(entities = [Todo::class], version = 1)

abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            if (instance == null){
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "tododb")
                    //.addMigrations(MIGRATION_1_2, MIGRATION_2_3) //переход на новую ДБ
                    .build()
                instance as TodoDatabase
            } else instance as TodoDatabase

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

    }

}