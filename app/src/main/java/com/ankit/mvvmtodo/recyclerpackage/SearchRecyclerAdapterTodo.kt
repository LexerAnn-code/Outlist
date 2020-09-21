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
import com.ankit.mvvmtodo.ui.EditTodoActivity
import com.ankit.mvvmtodo.ui.HomeActivity

class SearchRecyclerAdapterTodo(private val host: Activity):ListAdapter<TodoFolder, SearchFolderViewHolder>(
        DIFF_UTIL
){

companion object{
    private val DIFF_UTIL: DiffUtil.ItemCallback<TodoFolder> =
        object:DiffUtil.ItemCallback<TodoFolder>(){
            override fun areItemsTheSame(oldItem: TodoFolder, newItem: TodoFolder): Boolean=oldItem==newItem

            override fun areContentsTheSame(oldItem: TodoFolder, newItem: TodoFolder): Boolean =newItem.folderId==oldItem.folderId

        }
}




    override fun onBindViewHolder(holderViewHolder: SearchFolderViewHolder, position: Int) {
val getCurrentItem=getItem(position)
holderViewHolder.binding.todoSearch=getCurrentItem

        holderViewHolder.itemView.setOnClickListener {



                host.startActivity(
                    Intent(host, HomeActivity::class.java).apply {
                        putExtra(HomeActivity.EXTRA,getCurrentItem)
                    }

                )

        }
        holderViewHolder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFolderViewHolder {
        val layoutInflaters= LayoutInflater.from(parent.context)
        val bindings=FoldersearchBinding.inflate(layoutInflaters)
        return  SearchFolderViewHolder(bindings)
    }


}