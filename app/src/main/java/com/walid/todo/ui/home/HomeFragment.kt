package com.walid.todo.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.walid.todo.base.BaseFragment
import com.walid.todo.database.MyDatabase
import com.walid.todo.database.model.Todo
import com.walid.todo.databinding.HomeFragmentBinding
import com.walid.todo.ui.editTask.EditTaskActivity
import java.util.*

class HomeFragment : BaseFragment() {
    lateinit var viewBinding: HomeFragmentBinding
    lateinit var homeAdapter: HomeAdapter
    var currentDate: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = HomeFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeAdapter = HomeAdapter(null)
        viewBinding.recTodo.adapter = homeAdapter
        setCalender()
        homeAdapter.onItemEditClicked = object : HomeAdapter.OnItemEditClicked {
            override fun onClick(todo: Todo) {
                updateTodo(todo)
            }
        }
        homeAdapter.onIsDoneClickListener = object : HomeAdapter.OnIsDoneClickListener{
            override fun onClick(todo: Todo) {
                todo.isDone = true
                MyDatabase.getInstance(requireContext())?.todoDoa()?.updateTodo(todo)
                loadTasks()
                showMessage("Task set done","ok",{_,_->}, cancelLabel = false)
            }

        }
    }

    private fun makeToDoTaskDone(todo: Todo) {
        Log.d("fsafsf", "makeToDoTask: dsaaaaaa")
    }

    fun updateTodo(todo: Todo) {
        var intent = Intent(requireContext(), EditTaskActivity::class.java)
        intent.putExtra("toDoList", todo)
        startActivity(intent)
    }

    init {
        currentDate.set(Calendar.HOUR, 0)
        currentDate.set(Calendar.MINUTE, 0)
        currentDate.set(Calendar.SECOND, 0)
        currentDate.set(Calendar.MILLISECOND, 0)
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun setCalender() {
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                currentDate.set(Calendar.DAY_OF_MONTH, date.day)
                currentDate.set(Calendar.MONTH, date.month - 1)
                currentDate.set(Calendar.YEAR, date.year)
                currentDate.set(Calendar.HOUR, 0)
                currentDate.set(Calendar.MINUTE, 0)
                currentDate.set(Calendar.SECOND, 0)
                currentDate.set(Calendar.MILLISECOND, 0)
                loadTasks()
            }
        }
        viewBinding.calendarView.selectedDate = CalendarDay.today()
    }


    fun loadTasks() {
        if (!isResumed) {
            return
        }
        val list = MyDatabase.getInstance(requireContext())?.todoDoa()
            ?.selectAllTodoByDate(currentDate.timeInMillis)
        homeAdapter.refreshTasks(list as MutableList<Todo>?)
    }


}