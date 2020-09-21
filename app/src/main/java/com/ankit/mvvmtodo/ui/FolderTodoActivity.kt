package com.ankit.mvvmtodo.ui


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.ankit.mvvmtodo.*
import com.ankit.mvvmtodo.util.*

import com.ankit.mvvmtodo.databinding.ActivityFolderTodoBinding
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.recyclerpackage.FolderRecyclerView
import com.ankit.mvvmtodo.recyclerpackage.SearchRecyclerAdapterTodo
import com.ankit.mvvmtodo.viewmodel.TodoViewModel
import kotlinx.android.synthetic.main.activity_folder_todo.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FolderTodoActivity : AppCompatActivity(), CellOnLongPress {
    companion object {
        const val EXTRA = "EXTRA_DETAILS"
    }

    private lateinit var binding: ActivityFolderTodoBinding
    var todoFolder: TodoFolder? = null

    val folderViewModels by viewModel<TodoViewModel>()
    private lateinit var adapter: FolderRecyclerView
    private lateinit var adapterSearch: SearchRecyclerAdapterTodo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val getToken= OptionPreference(this@FolderTodoActivity)
        binding =
            DataBindingUtil.setContentView(
                this@FolderTodoActivity,
                    R.layout.activity_folder_todo
            )
        binding.folderViewModel = folderViewModels
        adapterSearch= SearchRecyclerAdapterTodo(this)
        adapter = FolderRecyclerView(this, this)

        binding.folderAdapter = adapter
        binding.folderAdapterSearch=adapterSearch

        setSupportActionBar(folder_toolbar)

        folderViewModels.allFolderList().observe(this, Observer {
            adapter.submitList(it)
            debugger("CHECKING $it")
            debugger("Folder${it.toString()}")
        })



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
                toastMessage("FOLDER ${folderName.text.toString()} CREATED")

            }

        }



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
        val searchManager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search_notes_folder)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0.toString().isNullOrEmpty()) {
                        debugger("Null")
                        folder_recyclerSearch.visibility = View.GONE
                        folder_recycler.visibility=View.VISIBLE
                    } else {
                        folder_recyclerSearch.visibility = View.VISIBLE
                        folder_recycler.visibility=View.GONE

                        folderViewModels.searchFolders("%$p0%")
                                .observe(this@FolderTodoActivity, Observer {
                                    debugger("Folders1$it")
                                    adapterSearch.submitList(it)
                                })



                    }
                    return true
                }
            })

        }


        return true
    }



private fun refresh(){
    folderViewModels.allFolderList().observe(this, Observer {
        adapter.submitList(it)

        debugger("Folder${it.toString()}")
    })
}



    override fun onCellOnLongPress(folders: TodoFolder) {

        BottomSheetFragementFolderMore(folders).apply {

            show(supportFragmentManager,this.tag)
        }
    }
}






