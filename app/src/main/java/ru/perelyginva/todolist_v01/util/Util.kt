package ru.perelyginva.todolist_v01.util

import android.content.Context
import androidx.room.Room
import ru.perelyginva.todolist_v01.model.TodoDatabase

const val DB_NAME = "tododb"

fun buildDB(context: Context): TodoDatabase {
    val db = Room.databaseBuilder(context,
        TodoDatabase::class.java, DB_NAME)//.addMigrations(MIGRATION_1_2, MIGRATION_2_3) //миграция на новую ДБ. migration to new DB
        .build()
    return db
}
//todo_table
//переход на новую БД. migration to new DB

//val MIGRATION_1_2 = object : Migration(1,2) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todo_table ADD COLUMN priority INTEGER DEFAULT 3 NOT NULL")
//    }
//
//}
//
//val MIGRATION_2_3 = object : Migration(2,3) {
//    override fun migrate(database: SupportSQLiteDatabase) {
//        database.execSQL("ALTER TABLE todo_table ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
//    }
//}
