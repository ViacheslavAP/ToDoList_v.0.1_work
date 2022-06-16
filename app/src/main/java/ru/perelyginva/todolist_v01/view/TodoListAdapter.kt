package ru.perelyginva.todolist_v01.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.perelyginva.todolist_v01.databinding.TodoItemLayoutBinding
import ru.perelyginva.todolist_v01.model.Todo

class TodoListAdapter(private val todoList: ArrayList<Todo>, val adapterOnClick: (Any) -> Unit) :

    RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>()  {
    class TodoListViewHolder(val binding: TodoItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        return TodoListViewHolder(TodoItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))

    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        //возможно ошибка! в оригинале вместо itemView идет view
        holder.binding.checkTask.text = todoList[position].title

        holder.binding.checkTask.setOnCheckedChangeListener { compoundButton, _ ->
            adapterOnClick(todoList[position])
        }
    }

    override fun getItemCount(): Int {
        return this.todoList.size
    }
}