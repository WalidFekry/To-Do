package com.walid.todo.ui.editTask

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.walid.todo.base.BaseActivity
import com.walid.todo.database.MyDatabase
import com.walid.todo.database.model.Todo
import com.walid.todo.databinding.ActivityEditTaskBinding
import java.text.SimpleDateFormat
import java.util.*

class EditTaskActivity : BaseActivity() {
    lateinit var viewBinding: ActivityEditTaskBinding
    lateinit var todo: Todo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        todo = (intent.getSerializableExtra("toDoList") as Todo)
        showData(todo)
        viewBinding.submit.setOnClickListener {
            updateData()
        }
        viewBinding.backIv.setOnClickListener {
            finish()
        }
    }

    private fun updateData() {
        if (isVaildForm()) {
            todo.todoName = viewBinding.titleContainer.editText?.text.toString()
            todo.todoDescription = viewBinding.descriptionContainer.editText?.text.toString()
            MyDatabase.getInstance(context = baseContext)?.todoDoa()?.updateTodo(todo)
            showMessage("Task updated successfully","ok",{_,_ ->}, cancelLabel = false)
        }
    }

    private fun isVaildForm(): Boolean {
        var isVaild = true
        if (viewBinding.titleContainer.editText?.text.toString().isBlank()) {
            isVaild = false
            viewBinding.titleContainer.editText?.error = "title can't be empty"
        } else {
            isVaild = true
            viewBinding.titleContainer.editText?.error = null
        }
        if (viewBinding.descriptionContainer.editText?.text.toString().isBlank()) {
            isVaild = false
            viewBinding.descriptionContainer.editText?.error = "description can't be empty"
        } else {
            isVaild = true
            viewBinding.descriptionContainer.editText?.error = null
        }
        return isVaild
    }

    private fun showData(todo: Todo) {
        viewBinding.titleContainer.editText?.setText(todo.todoName)
        viewBinding.descriptionContainer.editText?.setText(todo.todoDescription)
        val date = convertLongToTime(todo.todoDate)
        viewBinding.dateContainer.text = date
    }

    private fun convertLongToTime(todoDate: Long?): String {
        val date = Date(todoDate!!)
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }
}