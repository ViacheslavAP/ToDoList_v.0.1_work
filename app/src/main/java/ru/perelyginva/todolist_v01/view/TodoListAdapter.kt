package ru.perelyginva.todolist_v01.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.todolist_v01.R
import ru.perelyginva.todolist_v01.databinding.TodoItemLayoutBinding
import ru.perelyginva.todolist_v01.model.Todo

class TodoListAdapter(private val todoList: ArrayList<Todo>, val adapterOnClick: (Any) -> Unit) :

    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>()  {
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
        //возможно ошибка! в оригинале вместо itemView идет view
        holder.view.todo = todoList[position]
       /* holder.binding.checkTask.text = todoList[position].title

        holder.binding.checkTask.setOnCheckedChangeListener { compoundButton, _ ->
            adapterOnClick(todoList[position])
        }*/
    }

    override fun getItemCount(): Int {
        return this.todoList.size
    }
}