package com.ankit.mvvmtodo.recyclerpackage

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ankit.mvvmtodo.databinding.FoldersearchBinding
import com.ankit.mvvmtodo.databinding.ItemViewBinding
import com.ankit.mvvmtodo.model.TodoFolder

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
//
//        holderViewHolder.itemView.setOnClickListener {
//
//            if(getCurrentItem.password==null){
//                host.startActivity(
//                    Intent(host, EditTodoActivity::class.java).apply {
//                        putExtra(EditTodoActivity.EXTRA_POST,getCurrentItem)
//                    }
//                )
//            }
//            else{
//                debugger("Locked")
//                host.startActivity(
//                    Intent(host, PasswordVerify::class.java).apply {
//                        putExtra(PasswordVerify.EXTRA_POST,getCurrentItem)
//                    }
//
//                )
//            }
//        }
        holderViewHolder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchFolderViewHolder {
        val layoutInflaters= LayoutInflater.from(parent.context)
        val bindings=FoldersearchBinding.inflate(layoutInflaters)
        return  SearchFolderViewHolder(bindings)
    }


}