package com.ankit.mvvmtodo.UI

import com.ankit.mvvmtodo.TodoFolder
import com.ankit.mvvmtodo.RecyclerAdapterTodo
import com.ankit.mvvmtodo.Util.debugger
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.TodoViewModel
import com.ankit.mvvmtodo.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA = "EXTRA"
    }

    private lateinit var postAdapter: RecyclerAdapterTodo
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapterTodo: RecyclerAdapterTodo
    val homeViewModel by viewModel<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        adapterTodo =
            RecyclerAdapterTodo(this)
        binding.adapters = adapterTodo
        //Setting toolbar
        setSupportActionBar(topAppbar)

        //Handles back press action
        topAppbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.viewModelTodo=homeViewModel

        //Checks and get the data passed from Folder to HomeActivity
        val passData = intent.getParcelableExtra<TodoFolder>(EXTRA)
        if (intent.hasExtra(EXTRA)) {
            title = passData.folderName
            add_note_fab.setOnClickListener {
                startActivity(Intent(this, AddActivity::class.java).apply {
                    putExtra(AddActivity.EXTRA, passData)

                })
            }
            //Submits the Live-data to recyclerView
            homeViewModel.passingFolderID(passData.folderId!!).observe(this, Observer {
                debugger("All Query->>${it}")
                adapterTodo.submitList(it)
            })


        }


        //Creates a bottom line seen in the itemView of the recyclerView
        RecyclerLeslie.addItemDecoration(
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
        val search = menu?.findItem(R.id.search_notes)
        val searchView= search?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                homeViewModel.searchOfferingThree("%$p0%").observe(this@HomeActivity, Observer {
                    debugger("SSSSSSSSSSSSS->>$it")
                    adapterTodo.submitList(it)
                })
//                homeViewModel.sssss().observe(this@HomeActivity, Observer {
//                    adapterTodo.notifyDataSetChanged()
//                })
                return true
            }


        })

        return super.onCreateOptionsMenu(menu)
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