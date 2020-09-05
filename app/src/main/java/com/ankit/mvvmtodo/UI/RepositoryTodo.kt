package com.ankit.mvvmtodo.UI

import com.ankit.mvvmtodo.TodoFolder
import com.ankit.mvvmtodo.TodoRecord
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankit.mvvmtodo.DataBase.TodoDao
import com.ankit.mvvmtodo.Util.debugger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepositoryTodo(private val todoDao: TodoDao) {
    var fid:Int?=null

    private val allTodoFolder:LiveData<List<TodoFolder>> = todoDao.getFolders()
   private val allTodo:LiveData<List<TodoRecord>> = todoDao.getRecords(fid)
    private val allTodoUn:LiveData<List<TodoRecord>> = todoDao.getRecordsAll()

    private val offeringMutableList2 = MutableLiveData<MutableList<TodoRecord>>()
    val offeringLiveData2: LiveData<MutableList<TodoRecord>> get() = offeringMutableList2


fun getAllQueryTodo(tid:Int):LiveData<List<TodoRecord>>{
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

    fun getAllFolders():LiveData<List<TodoFolder>>{
        return allTodoFolder
    }

    fun getTodoAll():LiveData<List<TodoRecord>>{
        return allTodoUn
    }

     fun searchOfferingData(search: String?):MutableList<TodoRecord> {
         debugger("SEARCH TWO->>$search")
       return    todoDao.searchTodo(search).apply {
           offeringMutableList2.postValue(this)
       }
          }
    fun searchOfferingTwo(search: String?):LiveData<List<TodoRecord>> {
        debugger("SEARCH TWO->>$search")
        return    todoDao.searchTodoOne(search,fid)
    }
    fun searchFolderRepo(searchFolder: String?):LiveData<List<TodoFolder>> {
                return  todoDao.searchFolder(searchFolder)
    }

    }

