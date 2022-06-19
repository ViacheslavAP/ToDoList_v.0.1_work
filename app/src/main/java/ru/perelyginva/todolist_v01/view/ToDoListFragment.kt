package ru.perelyginva.todolist_v01.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import ru.perelyginva.todolist_v01.databinding.FragmentToDoListBinding
import ru.perelyginva.todolist_v01.model.Todo
import ru.perelyginva.todolist_v01.viewmodel.ListTodoViewModel


class ToDoListFragment : Fragment() {

    private lateinit var viewModel: ListTodoViewModel

    private var todoListAdapterNew: TodoListAdapter = TodoListAdapter(
        arrayListOf()
    ) { item -> doClick(item) }
    private lateinit var toDoListBinding: FragmentToDoListBinding

    private fun doClick(item: Any) {
        viewModel.clearTask(item as Todo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        toDoListBinding = FragmentToDoListBinding.inflate(layoutInflater, container, false)
        return toDoListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        toDoListBinding.recViewTodoList.layoutManager = LinearLayoutManager(context)
        toDoListBinding.recViewTodoList.adapter = todoListAdapterNew

        toDoListBinding.fabAdd.setOnClickListener {
            val action = ToDoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.todoLD.observe(
            viewLifecycleOwner, Observer {
                todoListAdapterNew.updateTodoList(it)
                with(toDoListBinding.tvEmpty) {
                    visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            })
    }

}