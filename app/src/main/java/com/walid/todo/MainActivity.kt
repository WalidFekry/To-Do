package com.walid.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.walid.todo.base.BaseActivity
import com.walid.todo.databinding.ActivityMainBinding
import com.walid.todo.ui.addTask.AddTaskFragment
import com.walid.todo.ui.addTask.OnDismissListener
import com.walid.todo.ui.home.HomeFragment
import com.walid.todo.ui.settings.SettingsFragment


class MainActivity : BaseActivity() {
    lateinit var viewBinding : ActivityMainBinding
    var homeFragment = HomeFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initViews()
    }

    private fun initViews() {
        viewBinding.bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.list){
                showFragment(homeFragment)
            }else if (it.itemId == R.id.settings){
                showFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true
        }
        viewBinding.bottomNavigationView.selectedItemId = R.id.list
        viewBinding.addTaskBtn.setOnClickListener {
            val addTaskFragment = AddTaskFragment()
            addTaskFragment.show(supportFragmentManager,null)
            addTaskFragment.onDismissListener = object : OnDismissListener {
                override fun onDismiss() {
                    homeFragment.loadTasks()
                }

            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.home_fragment,fragment).addToBackStack("").commit()
    }

}