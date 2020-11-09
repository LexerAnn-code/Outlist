package com.ankit.mvvmtodo.ui

import android.app.SearchManager
import android.content.Context
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.recyclerpackage.RecyclerAdapterTodo
import com.ankit.mvvmtodo.util.debugger
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.viewmodel.TodoViewModel
import com.ankit.mvvmtodo.databinding.ActivityHomeBinding
import com.ankit.mvvmtodo.recyclerpackage.SearchNotesRecyclerprivate
import com.ankit.mvvmtodo.util.LoadingState
import kotlinx.android.synthetic.main.activity_folder_todo.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA = "EXTRA"
    }

    private lateinit var postAdapter: RecyclerAdapterTodo
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterTodo: RecyclerAdapterTodo
    private lateinit var adapterTodoSearch: SearchNotesRecyclerprivate
    val homeViewModel by viewModel<TodoViewModel>()
    private lateinit var pass:TodoFolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        adapterTodo =
                RecyclerAdapterTodo(this)
        adapterTodoSearch= SearchNotesRecyclerprivate(this)
        binding.adapters = adapterTodo
        binding.adaptersSearch=adapterTodoSearch
        //Setting toolbar
        setSupportActionBar(topAppbar)

//handleIntent(intent)
        //Handles back press action
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.viewModelTodo=homeViewModel

        //Checks and get the data passed from Folder to HomeActivity
        pass = intent.getParcelableExtra(EXTRA)
        if (intent.hasExtra(EXTRA)) {
            title = pass.folderName
            add_note_fab.setOnClickListener {
                startActivity(Intent(this, AddActivity::class.java).apply {
                    putExtra(AddActivity.EXTRA, pass)
                })
            }

            //Submits the Live-data to recyclerView


        }

        homeViewModel.allTodoInFolder(pass.folderId).observe(this, Observer {
            adapterTodo.submitList(it)
        })
//        lifecycleScope.launch{
//            adapterTodo.loadStateFlow.collectLatest {loadStates ->
//                progressBar.isVisible=loadStates.refresh is LoadState.Loading
//                progressBarMore.isVisible=loadStates.append is LoadState.Loading
//
//
//            }
//        }



        //Creates a bottom line seen in the itemView of the recyclerView
        recycler_notes.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Binds menuItems to tool Bar
        menuInflater.inflate(R.menu.top_home, menu)
        //Handles the search Bar in the tool Bar
        //
        val searchManager=getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search_notes)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))

                this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {

                        if (p0.toString().isEmpty()) {
                            debugger("Null")
                            recycler_notes.visibility = View.VISIBLE
                            recycler_search.visibility= View.GONE
                        }
                        else {
                            recycler_notes.visibility = View.GONE
                            recycler_search.visibility= View.VISIBLE

                            pass.folderId?.let {
                                homeViewModel.searchNotes("%$p0%", pass.folderId!!).observe(this@HomeActivity, Observer {
                                    debugger("pol${pass.folderId}")
                                    debugger("SSSSSSSSSSSSS->>$it")

                                    adapterTodoSearch.submitList(it)


                                })
                            }
                        }
                        return true
                    }

                })



        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        //Handling click events on the items in the toolbar
        R.id.search_notes -> {
            Toast.makeText(this, "Search", Toast.LENGTH_LONG).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)

        }
    }

}