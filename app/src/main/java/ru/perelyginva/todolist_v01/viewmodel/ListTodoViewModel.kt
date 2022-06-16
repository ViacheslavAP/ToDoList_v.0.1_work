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

class ListTodoViewModel (application: Application):
    AndroidViewModel(application), CoroutineScope {

    val todoLD = MutableLiveData<List<Todo>>()
    private var job = Job()

    fun refresh(){
        launch {
            val db = buildDB(getApplication())
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo: Todo){
        launch {
            val db = buildDB(getApplication())
            db.todoDao().todoDone(todo.uuid)
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}