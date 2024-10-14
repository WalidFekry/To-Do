package com.walid.todo.ui.home

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.walid.todo.R
import com.walid.todo.database.model.Todo
import com.walid.todo.databinding.HomeItemBinding

class HomeAdapter(private var list: MutableList<Todo>?) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var onIsDoneClickListener: OnIsDoneClickListener? = null
    var onItemEditClicked: OnItemEditClicked? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }


    fun refreshTasks(newList: MutableList<Todo>?){
        list = newList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding.todoTitle.text = list!![position].todoName
        holder.viewBinding.todoDes.text = list!![position].todoDescription
        if (list!!.get(position).isDone!!) {
            holder.viewBinding.view.setBackgroundColor(Color.GREEN)
            holder.viewBinding.todoTitle.setTextColor(Color.GREEN)
            holder.viewBinding.editIv.setBackgroundColor(Color.GREEN)
            holder.viewBinding.isDoneIv.visibility = View.INVISIBLE
            holder.viewBinding.done.visibility = View.VISIBLE
        }else{
            holder.viewBinding.view.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,
                R.color.blue))
            holder.viewBinding.todoTitle.setTextColor(ContextCompat.getColor(holder.itemView.context,
                R.color.blue))
            holder.viewBinding.editIv.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.blue))
            holder.viewBinding.isDoneIv.visibility = View.VISIBLE
            holder.viewBinding.done.visibility = View.INVISIBLE
        }
        if (onItemEditClicked != null) {
            holder.viewBinding.editIv.setOnClickListener {
                onItemEditClicked?.onClick(list!![position])
            }
        }
        if (onIsDoneClickListener != null) {
            holder.viewBinding.isDoneIv.setOnClickListener {
                onIsDoneClickListener!!.onClick(list!![position])
            }
        }

    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(val viewBinding: HomeItemBinding) : RecyclerView.ViewHolder(viewBinding.root)


    interface OnIsDoneClickListener {
        fun onClick(todo: Todo)
    }

    interface OnItemEditClicked {
        fun onClick(todo: Todo)
    }

}