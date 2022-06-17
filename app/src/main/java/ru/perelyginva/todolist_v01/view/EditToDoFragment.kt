package ru.perelyginva.todolist_v01.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.perelyginva.todolist_v01.R
import ru.perelyginva.todolist_v01.databinding.FragmentEditToDoBinding
import ru.perelyginva.todolist_v01.model.Todo
import ru.perelyginva.todolist_v01.viewmodel.DetailTodoViewModel


class EditToDoFragment : Fragment(), RadioButtonClickListener, TodoSaveChangesListener {
    private lateinit var dataBinding: FragmentEditToDoBinding
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditToDoBinding>(
            inflater, R.layout.fragment_edit_to_do, container, false)
        return dataBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener = this
        dataBinding.radiolistener = this
        /* dataBinding.btnAdd.setOnClickListener {
             val radioGroup = dataBinding.radioGroupPriorety.checkedRadioButtonId
             viewModel.update(
                 dataBinding.tvTitle.text.toString(),
                 dataBinding.tvNote.text.toString(),
                 radioGroup.toString().toInt(),
                 uuid)
             //TODO("перевести")
             Toast.makeText(view.context, "Todo update", Toast.LENGTH_SHORT).show()
         }
 */
        observerViewModel()
    }

    private fun observerViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
            /*dataBinding.tvTitle.setText(it.title)
            dataBinding.tvNote.setText(it.notes)
//проверяем на то, какая из радио кнопок нажата
            when (it.priority) {
                3 -> dataBinding.radioHigh.isChecked = true
                2 -> dataBinding.radioMedium.isChecked = true
                else -> dataBinding.radioLow.isChecked = true
            }*/
        })
    }

    override fun onRadioButtonClick(view: View, objectRadio: Todo) {
        //TODO("выкидывает сюда с ошибкой!!! For input string: "false"")
        objectRadio.priority = view.tag.toString().toInt()
    }

    override fun onTodoSaveChanges(view: View, objectSave: Todo) {
        //TODO("не сохраняет изменения! Проверить")
        Log.d("TAG", objectSave.toString())
     //   viewModel.update(objectSave.title, objectSave.notes, objectSave.priority, objectSave.uuid)
     //   Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
    }

}