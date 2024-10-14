package com.walid.todo.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBindings
import com.walid.todo.R
import com.walid.todo.databinding.SettingsFragmentBinding

class SettingsFragment : Fragment(){
    lateinit var viewBindings: SettingsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBindings = SettingsFragmentBinding.inflate(inflater,container,false)
        return viewBindings.root
    }

}

