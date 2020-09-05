package com.ankit.mvvmtodo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.mvvmtodo.UI.RepositoryTodo
import com.ankit.mvvmtodo.Util.debugger
import kotlinx.coroutines.launch
class TodoViewModel(private val todoRepository: RepositoryTodo):ViewModel() {
    var searchTitle: String? = null
    private val allFolder: LiveData<List<TodoFolder>> = todoRepository.getAllFolders()

    private val allTodoAll: LiveData<List<TodoRecord>> = todoRepository.getTodoAll()

    fun passingFolderID(uid: Int):LiveData<List<TodoRecord>> {
       todoRepository.fid = uid
        debugger("Passed ID->${todoRepository.fid}")

     return  todoRepository.getAllQueryTodo(uid)
    }


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

    fun getAllFolder(): LiveData<List<TodoFolder>> {
        return allFolder
    }


    fun getAlltodoUn(): LiveData<List<TodoRecord>> {
       return allTodoAll
    }

    fun searchOffering(input: String?):MutableList<TodoRecord> {
        //Search Method
        debugger("SEARCH-?>>>$input")
        return     todoRepository.searchOfferingData(input)
    }
    fun searchOfferingThree(input: String?):LiveData<List<TodoRecord>> {
        //Search Method
        debugger("SEARCH-?>>>$input")
        return     todoRepository.searchOfferingTwo(input)
    }
    fun searchFoldersViewModel(input: String?):LiveData<List<TodoFolder>>{
        return  todoRepository.searchFolderRepo(input)
    }
    fun sssss(): LiveData<MutableList<TodoRecord>>{
        return  todoRepository.offeringLiveData2
    }

}


