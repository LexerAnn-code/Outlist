package com.ankit.mvvmtodo.ui

import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.model.TodoRecord
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankit.mvvmtodo.database.TodoDao
import com.ankit.mvvmtodo.util.debugger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryTodo(private val todoDao: TodoDao) {
    var fid:Int?=null

    private val allTodoFolder= todoDao.getFolders()




fun getAllQueryTodo(tid:Int):LiveData<MutableList<TodoRecord>>{
      return  todoDao.getRecords(tid)
}


suspend  fun saveTodo(todoRecord: TodoRecord){
      withContext(Dispatchers.IO){
          todoDao.saveTodoDTO(todoRecord)
      }
  }
    suspend fun pinTodo(todoRecord: TodoRecord){
        withContext(Dispatchers.IO){
            todoDao.savePinTodoRecord(todoRecord)
        }
    }
    suspend  fun saveTodoFolder(todoFolder: TodoFolder){
        withContext(Dispatchers.IO){
            todoDao.saveTodoInFolder(todoFolder)
        }
    }


    suspend fun updateTodo(todoRecord: TodoRecord){
        withContext(Dispatchers.IO){
            todoDao.updateTodoDTO(todoRecord)
        }
    }
    suspend fun updateFolders(todoFolder: TodoFolder){
        withContext(Dispatchers.IO){
            todoDao.updateFolder(todoFolder)
        }
    }
    suspend fun deleteTodo(todoRecord: TodoRecord){
        withContext(Dispatchers.IO){
            todoDao.deleteTodoDTO(todoRecord)
        }
    }
    suspend fun deleteFolder(todoFolder: TodoFolder){
        withContext(Dispatchers.IO){
            todoDao.deleteFolder(todoFolder)
        }
    }

    fun getAllFolders():LiveData<MutableList<TodoFolder>>{
        return allTodoFolder
    }




    fun searchNotesRepo(search: String?):LiveData<MutableList<TodoRecord>> {
        debugger("SEARCH TWO->>$search")
        return    todoDao.searchTodoOne(search,fid)
    }
    fun searchFolderRepo(searchFolder: String?):LiveData<MutableList<TodoFolder>> {
                return  todoDao.searchFolder(searchFolder)
    }

    }

