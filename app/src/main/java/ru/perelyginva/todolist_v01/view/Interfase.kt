package ru.perelyginva.todolist_v01.view

import android.view.View
import android.widget.CompoundButton
import ru.perelyginva.todolist_v01.model.Todo

interface TodoCheckChangeListener {
    fun onTodoCheckChange(cb: CompoundButton, isChecked: Boolean, objectCheck: Todo)
}

interface TodoEditClickListener{
    fun onTodoEditClick(view: View)
}

interface RadioButtonClickListener{
    fun onRadioButtonClick(view: View, objectRadio: Todo)
}

interface TodoSaveChangesListener{
    fun onTodoSaveChanges(view: View, objectSave: Todo)
}