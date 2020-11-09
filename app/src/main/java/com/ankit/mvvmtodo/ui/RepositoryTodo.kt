package com.ankit.mvvmtodo.ui

import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.model.TodoRecord
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.ankit.mvvmtodo.database.TodoDao

import com.ankit.mvvmtodo.util.debugger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RepositoryTodo(private val todoDao: TodoDao) {


    private val allTodoFolder= todoDao.getFolders()


//fun getPagingData(dao: TodoDao):Flow<PagingData<TodoRecord>>{
//
//    return Pager(
//            config = PagingConfig(
//                    pageSize = 50,
//                    enablePlaceholders = false
//            ),pagingSourceFactory = {DataSource(dao,fid!!)}
//    ).flow
//}

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




    fun     searchNotesRepo(search: String?,uid:Int):LiveData<MutableList<TodoRecord>> {
        debugger("SEARCH TWO->>$search")
        debugger("UID->>$uid")
        return    todoDao.searchTodoOne(uid = uid,search = search)
    }
    fun searchFolderRepo(searchFolder: String?):LiveData<MutableList<TodoFolder>> {
                return  todoDao.searchFolder(searchFolder)
    }

    }

