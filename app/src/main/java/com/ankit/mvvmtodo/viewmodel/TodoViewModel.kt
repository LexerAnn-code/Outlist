package com.ankit.mvvmtodo.viewmodel

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.ankit.mvvmtodo.database.TodoDao
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.model.TodoRecord
import com.ankit.mvvmtodo.ui.RepositoryTodo

import com.ankit.mvvmtodo.util.LoadingState
import com.ankit.mvvmtodo.util.debugger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
class TodoViewModel(private val todoRepository: RepositoryTodo,private val dao: TodoDao):ViewModel() {
    var uuid:Int?=null
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _loadingState





//val flow:Flow<PagingData<TodoRecord>> = Pager(
//PagingConfig(pageSize = 2)){
//
//    DataSource(dao,uuid!!)
//}.flow.cachedIn(viewModelScope)
//

//        val flow = Pager(
//                PagingConfig(pageSize =4,maxSize = 40)) {
//         dao.getRecords(uuid)
//        }.flow.cachedIn(viewModelScope)
//
//fun allList():LiveData<MutableList<TodoRecord>>{
//  _data.postValue( dao.getRecords(uuid))
//}
//    fun passingFolderID(uid: Int):LiveData<Paged<TodoRecord>> {
//       todoRepository.fid = uid
//        debugger("Passed ID->${todoRepository.fid}")
//     return  todoRepository.getAllQueryTodo(uid).toLiveData(pageSize = 5)
//    }



    fun saveTodo(todoRecord: TodoRecord) {
        viewModelScope.launch {
            todoRepository.saveTodo(todoRecord)

            debugger("Passed to Repo")
        }

    }

    fun saveTodoModelFolder(todoFolder: TodoFolder) {
        viewModelScope.launch {
            todoRepository.saveTodoFolder(todoFolder)
            debugger("Passed to Todo Folder")
        }

    }
    fun updateTodoWithPin(todoRecord: TodoRecord){
        viewModelScope.launch {
            todoRepository.pinTodo(todoRecord)
        }
    }


    fun upDateTodo(todoRecord: TodoRecord) {
        viewModelScope.launch {
            todoRepository.updateTodo(todoRecord)
            debugger("Update Passed")
        }
    }
    fun upDateFoldersView(todoFolder: TodoFolder){
        viewModelScope.launch {
            todoRepository.updateFolders(todoFolder)
        }
    }

    fun deleteTdo(todoRecord: TodoRecord) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todoRecord)
        }
    }
    fun deleteFoldersView(todoFolder: TodoFolder){
        viewModelScope.launch {
            todoRepository.deleteFolder(todoFolder)
        }
    }
    fun allFolderList():LiveData<MutableList<TodoFolder>>{
        return todoRepository.getAllFolders()
    }








    fun searchNotes(input: String?,uid:Int):LiveData<MutableList<TodoRecord>> {
        debugger("SEARCH-?>>>$uid")
        return     todoRepository.searchNotesRepo(input,uid)
    }
    fun searchFolders(input: String?):LiveData<MutableList<TodoFolder>>{
        return  todoRepository.searchFolderRepo(input)
    }
    fun allTodoInFolder(uuid:Int?):LiveData<MutableList<TodoRecord>>{
  return dao.getRecordsAll(uuid)

    }



}


