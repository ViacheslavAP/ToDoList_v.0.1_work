package ru.perelyginva.todolist_v01.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.todolist_v01.R
import ru.perelyginva.todolist_v01.databinding.TodoItemLayoutBinding
import ru.perelyginva.todolist_v01.model.Todo

class TodoListAdapter(private val todoList: ArrayList<Todo>, val adapterOnClick: (Any) -> Unit) :
    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(), TodoCheckChangeListener, TodoEditClickListener{
    class TodoListViewHolder(val view: TodoItemLayoutBinding) : RecyclerView.ViewHolder(view.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater, R.layout.todo_item_layout,
        parent, false)
        return TodoListViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {

        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this

    }

    override fun getItemCount(): Int {
        return this.todoList.size
    }

    override fun onTodoCheckChange(cb: CompoundButton, isChecked: Boolean, objectCheck: Todo) {
        if (isChecked){
            adapterOnClick(objectCheck)
        }
    }

    override fun onTodoEditClick(view: View) {
        val action = ToDoListFragmentDirections.actionEditToDoFragment(view.tag.toString().toInt())
        Navigation.findNavController(view).navigate(action)
    }
}