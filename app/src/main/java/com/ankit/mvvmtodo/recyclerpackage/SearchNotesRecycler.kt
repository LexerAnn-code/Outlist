package com.ankit.mvvmtodo.recyclerpackage

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ankit.mvvmtodo.databinding.FoldersearchBinding
import com.ankit.mvvmtodo.databinding.ItemViewBinding
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.model.TodoRecord
import com.ankit.mvvmtodo.ui.EditTodoActivity
import com.ankit.mvvmtodo.ui.PasswordVerify
import com.ankit.mvvmtodo.util.debugger

class SearchNotesRecyclerprivate (val host: Activity): ListAdapter<TodoRecord, SearchNotesViewHolder>(DIFF_UTIL) {
    companion object{
        private val DIFF_UTIL: DiffUtil.ItemCallback<TodoRecord> =
                object: DiffUtil.ItemCallback<TodoRecord>(){
                    override fun areItemsTheSame(oldItem: TodoRecord, newItem: TodoRecord): Boolean=oldItem==newItem

                    override fun areContentsTheSame(oldItem: TodoRecord, newItem: TodoRecord): Boolean =newItem.title==oldItem.title

                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchNotesViewHolder {
        val layoutInflaters= LayoutInflater.from(parent.context)
        val bindings= ItemViewBinding.inflate(layoutInflaters)
        return  SearchNotesViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: SearchNotesViewHolder, position: Int) {
        val getCurrentItem=getItem(position)
        holder.binding.todo=getCurrentItem
        holder.itemView.setOnClickListener {
            if(getCurrentItem?.password==null){
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
        }
    }

