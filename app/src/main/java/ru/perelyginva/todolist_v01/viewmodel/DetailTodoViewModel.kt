package ru.perelyginva.todolist_v01.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.perelyginva.todolist_v01.model.Todo
import ru.perelyginva.todolist_v01.util.buildDB
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel (application: Application) :
    AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    fun fetch(uuid: Int){
        launch { val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectTodo(uuid)}
    }

    fun update(title:String, notes:String, priority:Int, uuid: Int){
        launch {
            val db = buildDB(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }

    fun addTodo( todo: Todo) {
        launch {
            val db = buildDB(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}