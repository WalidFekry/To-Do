package com.walid.todo.ui.addTask

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.walid.todo.database.MyDatabase
import com.walid.todo.database.model.Todo
import com.walid.todo.databinding.AddTaskFragmentBinding
import java.util.*

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var viewBinding: AddTaskFragmentBinding
    val currentDate: Calendar = Calendar.getInstance()
    var onDismissListener: OnDismissListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = AddTaskFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.onDismiss()
    }

    init {
        currentDate.set(Calendar.HOUR, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)
    }

    private fun initViews() {
        viewBinding.selectedDate.setOnClickListener {
            showDatePicker()
        }
        viewBinding.addTaskBtn.setOnClickListener {
            if (validateForm()) {
                context?.let { it1 ->
                    MyDatabase.getInstance(it1)?.todoDoa()?.insertTodo(
                        Todo(
                            todoName = viewBinding.taskTitleEt.text.toString(),
                            todoDescription = viewBinding.taskDesEt.text.toString(),
                            todoDate = currentDate.timeInMillis
                        )
                    )
                    showDialogDone()
                }
            }
        }
    }

    private fun showDialogDone() {
        val dialogDone = AlertDialog.Builder(requireContext()).setMessage("Task Inserted Successfully")
            .setPositiveButton("OK") { p0, p1 ->
                p0?.dismiss()
                dismiss()
            }.setCancelable(false)
        dialogDone.show()
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                currentDate.set(Calendar.YEAR, i)
                currentDate.set(Calendar.MONTH, i2)
                currentDate.set(Calendar.DAY_OF_MONTH, i3)
                viewBinding.selectedDate.text = "$i3-${i2.plus(1)}-$i"
            },
            currentDate.get(Calendar.YEAR),
            currentDate.get(Calendar.MONTH),
            currentDate.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun validateForm(): Boolean {
        var isValid = true
        if (viewBinding.taskTitleEt.text?.isBlank()!!) {
            isValid = false
            viewBinding.taskTitleEt.error = "Enter title"
        } else {
            viewBinding.taskTitleEt.error = null
            isValid = true
        }
        if (viewBinding.taskDesEt.text?.isBlank()!!) {
            isValid = false
            viewBinding.taskDesEt.error = "Enter description"
        } else {
            viewBinding.taskDesEt.error = null
            isValid = true
        }
        if (viewBinding.selectedDate.text.equals("Select Date")) {
            isValid = false
            Toast.makeText(context, "Please select date!", Toast.LENGTH_SHORT).show()
        } else {
            isValid = true
        }
        return isValid
    }
}




