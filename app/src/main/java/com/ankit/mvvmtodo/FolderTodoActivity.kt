package com.ankit.mvvmtodo


import android.app.ProgressDialog.show
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ankit.mvvmtodo.Util.*

import com.ankit.mvvmtodo.databinding.ActivityFolderTodoBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_folder_todo.*
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderTodoActivity : AppCompatActivity(),CellOnLongPress {
    companion object {
        const val EXTRA = "EXTRA_DETAILS"
    }

    private lateinit var binding: ActivityFolderTodoBinding
    var todoFolder: TodoFolder? = null

    val folderViewModels by viewModel<TodoViewModel>()
    private lateinit var adapter: FolderRecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getToken=OptionPreference(this@FolderTodoActivity)
        binding =
            DataBindingUtil.setContentView(
                this@FolderTodoActivity,
                R.layout.activity_folder_todo
            )
        binding.folderViewModel = folderViewModels
        adapter = FolderRecyclerView(this,this)
        binding.folderAdapter = adapter
        setSupportActionBar(folder_toolbar)




//            if(getToken.getCheck("${Constants.PREFS_AUTH_VALUE}")!=null){
//                toastMessage("Visible")
//                folder_more_edit.setOnClickListener(View.OnClickListener {
//                    Toast.makeText( this@FolderTodoActivity.applicationContext,"TRIAL", Toast.LENGTH_SHORT).show()
//
//                })
//                folder_more_delete.setOnClickListener {
//                    toastMessage("DELETE")
//                }
//                folder_more_folderName.setOnClickListener {
//                    toastMessage("WORKD")
//                }
//
//
//            }





        folderNameButtonCancel.setOnClickListener {
            folderNameLayout.visibility = View.GONE
            debugger("CANCELLLLLLL")


        }
        folderNameButton.setOnClickListener {

            if (folderName.text.isNullOrEmpty())
            {
                FolderInputLayout.error=getString(R.string.errorEmptyFolder)
                folderName.addTextChangedListener(object :TextWatcher{
                    override fun afterTextChanged(p0: Editable?) {

                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                        if(p0.isNullOrEmpty()){

                        }
                        else{
                            FolderInputLayout.error=null
                        }
                    }
                })


            }
            else {
                val todoFolderId = if (todoFolder != null) todoFolder!!.folderId else null
                val folderDetails = TodoFolder(
                    // changed folderId = todoFolderId,
                    folderName = folderName.text.toString()
                )
                folderViewModels.saveTodoModelFolder(folderDetails)
                folderNameLayout.visibility = View.GONE
                debugger("Clicked from View${todoFolderId.toString()}")
                folderName.setText("")
                FolderInputLayout.error=null
            }
        }


        folderViewModels.getAllFolder().observe(this, Observer {
            adapter.notifyDataSetChanged()
            adapter.submitList(it)
            debugger("Folder${it.toString()}")
        })
//        search_folder_text.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(p0: Editable?) {
//                folderViewModels.getAllFolder().observe(this@FolderTodoActivity, Observer {
//
//                    adapter.submitList(it)
//
//                    debugger("Folder3${it.toString()}")
//                })
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//
//                folderViewModels.getAllFolder().observe(this@FolderTodoActivity, Observer {
//
//                    adapter.submitList(it)
//
//                    debugger("Folder2${it.toString()}")
//                })
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                folderViewModels.searchFoldersViewModel("%$p0%").observe(this@FolderTodoActivity,
//                    Observer {
//                        debugger("Folders1$it")
//                        adapter.notifyDataSetChanged()
//                        adapter.submitList(it)
//
//                    })
//            }
//
//        })
        BottomNavHome.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.addFolderItem -> {
                     folderNameLayout.visibility = View.VISIBLE


                    true
                }

                else -> false
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.folder_home_menu, menu)
        val search = menu?.findItem(R.id.search_notes_folder)
        val searchView= search?.actionView as SearchView

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                folderViewModels.searchFoldersViewModel("%$p0%")
                    .observe(this@FolderTodoActivity, Observer {
                        debugger("Folders1$it")
                        adapter.notifyDataSetChanged()
                        adapter.submitList(it)

                    })
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }







    override fun onCellOnLongPress(folders: TodoFolder) {

        BottomSheetFragementFolderMore(folders).apply {

            show(supportFragmentManager,this.tag)
        }
    }
}





//    private  fun saveFolder () {
//        val todoFolderId= if(todoFolder!=null) todoFolder?.folderId else null
//        val folderDetails= TodoFolder(
//            folderId = todoFolderId,
//            folderName =
//
//        )
//        debugger("Clicked from View$todoFolderId")
//        postViewModel.saveTodo(message)
//    }

