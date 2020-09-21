package com.ankit.mvvmtodo.recyclerpackage

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ankit.mvvmtodo.util.OptionPreference
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.ui.HomeActivity
import com.ankit.mvvmtodo.databinding.AddfolderlayoutBinding
import com.ankit.mvvmtodo.util.CellOnLongPress
import kotlinx.android.synthetic.main.activity_folder_todo.*

class FolderRecyclerView (private val hosty:Activity,private  val  cellOnLongPress: CellOnLongPress): ListAdapter<TodoFolder, FolderViewHolder>(
        DIFF_UTIL
){
    companion object{
        val DIFF_UTIL: DiffUtil.ItemCallback<TodoFolder> =
            object :DiffUtil.ItemCallback<TodoFolder>(){
                override fun areItemsTheSame(oldItem: TodoFolder, newItem: TodoFolder): Boolean =oldItem.folderId==newItem.folderId

                override fun areContentsTheSame(oldItem: TodoFolder, newItem: TodoFolder): Boolean=oldItem.folderName==newItem.folderName

            }

    }
    val prefs= OptionPreference(hosty.applicationContext)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding=AddfolderlayoutBinding.inflate(layoutInflater)
        return FolderViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
       val getItemPosition=getItem(position)

        holder.binding.todoFolderVariable=getItemPosition
        holder.itemView.setOnClickListener {
            if(hosty.folderNameLayout.visibility==View.VISIBLE){
                hosty.folderNameLayout.visibility=View.GONE
            }
            hosty.startActivity(Intent(hosty,HomeActivity::class.java).apply {
                putExtra(HomeActivity.EXTRA,getItemPosition)

            })

            }
        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
         //hosty.startActivity(Intent(hosty,FolderOptionMenu::class.java))
//
//            hosty.folderNameMoreOptions.visibility=View.VISIBLE.apply {
//                hosty.folder_recycler.visibility=View.GONE
//                prefs.storeCheck("ACTIVE")
//               hosty.folder_more_folderName.text=getItemPosition.folderName
//            }
            cellOnLongPress.onCellOnLongPress(getItemPosition)

            return@OnLongClickListener true

        })



        holder.binding.executePendingBindings()

    }


}