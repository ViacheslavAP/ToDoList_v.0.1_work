package ru.perelyginva.todolist_v01.model

import androidx.room.*



//TODO("создается две таблицы!! исправить")
@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo)

    @Query("SELECT * FROM todo_table WHERE is_done = 0 ORDER BY priority DESC")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo_table WHERE uuid = :id")
    suspend fun selectTodo(id:Int): Todo

    @Query("UPDATE todo_table SET title= :title, notes= :notes, priority= :priority WHERE uuid= :uuid")
    suspend fun update(title: String, notes:String, priority: Int, uuid: Int)

    @Query("UPDATE todo_table SET is_done = 1 WHERE uuid = :id")
    suspend fun todoDone(id: Int)

    @Delete
    suspend fun deleteTodo(todo: Todo)
}