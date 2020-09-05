package com.ankit.mvvmtodo

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ankit.mvvmtodo.UI.EditTodoActivity

import com.ankit.mvvmtodo.Util.debugger
import android.view.View
import android.widget.Filter
import android.widget.Filterable
import com.ankit.mvvmtodo.Util.PasswordVerify
import com.ankit.mvvmtodo.databinding.ItemViewBinding
import kotlinx.android.synthetic.main.activity_folder_todo.*
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerAdapterTodo(private val host: Activity):ListAdapter<TodoRecord, TodoViewHolder>(
    DIFF_UTIL
),Filterable{

companion object{
    private val DIFF_UTIL: DiffUtil.ItemCallback<TodoRecord> =
        object:DiffUtil.ItemCallback<TodoRecord>(){
            override fun areItemsTheSame(oldItem: TodoRecord, newItem: TodoRecord): Boolean=oldItem==newItem

            override fun areContentsTheSame(oldItem: TodoRecord, newItem: TodoRecord): Boolean =newItem.todoId==oldItem.todoId

        }
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
val binding=ItemViewBinding.inflate(layoutInflater)
return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holderViewHolder: TodoViewHolder, position: Int) {
val getCurrentItem=getItem(position)
holderViewHolder.binding.todo=getCurrentItem
        if(getCurrentItem.password!=null){
            holderViewHolder.itemView.lockTodo.visibility=View.VISIBLE
        }
        holderViewHolder.itemView.setOnClickListener {

            if(getCurrentItem.password==null){
                host.startActivity(
                    Intent(host, EditTodoActivity::class.java).apply {
                        putExtra(EditTodoActivity.EXTRA_POST,getCurrentItem)
                    }
                )
            }
            else{
                debugger("Locked")
                host.startActivity(
                    Intent(host, PasswordVerify::class.java).apply {
                        putExtra(PasswordVerify.EXTRA_POST,getCurrentItem)
                    }

                )
            }
        }
        holderViewHolder.binding.executePendingBindings()
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }

}