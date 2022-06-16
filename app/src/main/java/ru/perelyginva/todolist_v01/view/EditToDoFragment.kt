package ru.perelyginva.todolist_v01.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.todolist_v01.databinding.FragmentCreateToDoBinding
import ru.perelyginva.todolist_v01.viewmodel.DetailTodoViewModel


class EditToDoFragment : Fragment() {
    private lateinit var createToDoBinding: FragmentCreateToDoBinding
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        createToDoBinding = FragmentCreateToDoBinding.inflate(layoutInflater, container, false)
        return createToDoBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        //TODO("убрать текст в string")
        createToDoBinding.tvToDo.text = "Edit ToDo"
        createToDoBinding.btnAdd.text = "Save Changes"

        createToDoBinding.btnAdd.setOnClickListener {
            val radioGroup = createToDoBinding.radioGroupPriorety.checkedRadioButtonId
            viewModel.update(
                createToDoBinding.tvTitle.text.toString(),
                createToDoBinding.tvNote.text.toString(),
                radioGroup.toString().toInt(),
                uuid)
            //TODO("перевести")
            Toast.makeText(view.context, "Todo update", Toast.LENGTH_SHORT).show()
        }

        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            createToDoBinding.tvTitle.setText(it.title)
            createToDoBinding.tvNote.setText(it.notes)
//проверяем на то, какая из радио кнопок нажата
            when (it.priority){
                3 -> createToDoBinding.radioHigh.isChecked = true
                2 -> createToDoBinding.radioMedium.isChecked = true
                else -> createToDoBinding.radioLow.isChecked = true
            }
        })
    }

}