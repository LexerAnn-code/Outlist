package com.ankit.mvvmtodo.UI

import com.ankit.mvvmtodo.TodoRecord
import com.ankit.mvvmtodo.Util.BottomSheetFragement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ankit.mvvmtodo.R
import com.ankit.mvvmtodo.Util.dateFormatter
import android.view.Menu
import android.view.MenuItem
import com.ankit.mvvmtodo.TodoViewModel
import com.ankit.mvvmtodo.Util.toastMessage
import kotlinx.android.synthetic.main.activity_this_edit.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditTodoActivity : AppCompatActivity() {
    private val postViewM by viewModel<TodoViewModel>()
    companion object {
        const val EXTRA_POST = "extra_post"
        const val EXTRA_POSTS = "extra_posts"
    }
    private lateinit var pinTodo: TodoRecord


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_this_edit)
        setSupportActionBar(editToolBar)
        title=""
        editToolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        pinTodo=intent.getParcelableExtra<TodoRecord>(EXTRA_POST)


                if(intent.hasExtra(EXTRA_POST)){
                title_main.setText(pinTodo.title)
                content_main.setText(pinTodo.content)
            }

//        val message=
//            TodoRecord(tododata.todoId, tododata.userCreatedFolderId,tododata.title, tododata.content)
//        deleteFloat.setOnClickListener {
//            postViewM.deleteTdo(message)
//        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem)=when(item.itemId) {
       R.id.more->{
           show(pinTodo)
             true
       }
        R.id.editTodoRecord->{
            updateTodo(pinTodo)
            true
        }
       else->{
            super.onOptionsItemSelected(item)
       }


    }

    private fun updateTodo(editTodoPass: TodoRecord){
        val updateTitle= title_main.text.toString()
        val updateContent= content_main.text.toString()
        val currentDate = dateFormatter()
        val upMessage= TodoRecord(
            editTodoPass.todoId,
            editTodoPass.userCreatedFolderId,
            updateTitle,
            updateContent,
            currentDate
        )
        postViewM.upDateTodo(upMessage)
        toastMessage("Updated")
    }

    private fun show(pinTodoPass: TodoRecord){
        BottomSheetFragement(pinTodoPass).apply {

            show(supportFragmentManager,this.tag)
        }
    }



}

