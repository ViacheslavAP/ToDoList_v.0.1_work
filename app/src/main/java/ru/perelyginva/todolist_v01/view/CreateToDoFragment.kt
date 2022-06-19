package ru.perelyginva.todolist_v01.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ru.perelyginva.todolist_v01.R
import ru.perelyginva.todolist_v01.databinding.FragmentCreateToDoBinding
import ru.perelyginva.todolist_v01.model.Todo
import ru.perelyginva.todolist_v01.util.NotificationHelper
import ru.perelyginva.todolist_v01.viewmodel.DetailTodoViewModel


class CreateToDoFragment : Fragment() {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var createToDoBinding: FragmentCreateToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        createToDoBinding = FragmentCreateToDoBinding.inflate(layoutInflater, container, false)
        return createToDoBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)


        createToDoBinding.btnAdd.setOnClickListener {
            //запускаем всплыввающие уведомления
            NotificationHelper(view.context).createNotification(
                "Todo created", "A new todo has been created!")
            val radioGroup = createToDoBinding.radioGroupPriorety.checkedRadioButtonId //создаем радиГруппу кнопок
            val todo = Todo(
                createToDoBinding.tvTitle.text.toString(),
                createToDoBinding.tvNote.text.toString(),radioGroup.toString().toInt(), 0)
            // Возможно не заработает!!!! в оригинате через .tag
            viewModel.addTodo(todo)
            //перевести
            Toast.makeText(it.context, getString(R.string.todo_created), Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }
    }


}