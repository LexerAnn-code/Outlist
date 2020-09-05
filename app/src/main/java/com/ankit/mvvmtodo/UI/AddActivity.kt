package com.ankit.mvvmtodo.UI


import com.ankit.mvvmtodo.TodoFolder
import com.ankit.mvvmtodo.TodoRecord
import com.ankit.mvvmtodo.Util.dateFormatter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.TodoViewModel
import com.ankit.mvvmtodo.Util.toastMessage
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_home.*

import org.koin.androidx.viewmodel.ext.android.viewModel

class AddActivity : AppCompatActivity() {
    companion object{
        const val EXTRA="EXTRA"
    }
    val postViewModel by viewModel <TodoViewModel>()
    private val todoRecord: TodoRecord?=null
    private var folderid:Int?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setSupportActionBar(addToolbar)
        title=""
addToolbar.setNavigationOnClickListener {
    onBackPressed()
}

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topappbarmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)=when(item.itemId) {
        R.id.Done-> {
                    notesDone()
            true
        }
        else->{
             super.onOptionsItemSelected(item)
        }

    }

    private fun notesDone() {
        if (intent.hasExtra(EXTRA)) {
            val id = intent.getParcelableExtra<TodoFolder>(EXTRA)
            val todoId = if (todoRecord != null) todoRecord.todoId else null
            val currentDate = dateFormatter()
            val message = TodoRecord(
                todoId,
                title = todoContentTitleInput.text.toString(),
                content = todoContentBodyInput.text.toString(),
                userCreatedFolderId = id?.folderId,
                date = currentDate
            )
            postViewModel.saveTodo(message)
            //toastMessage("Notes Saved")

            onBackPressed()
        }
    }

}
