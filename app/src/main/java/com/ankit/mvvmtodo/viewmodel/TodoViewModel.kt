package com.ankit.mvvmtodo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankit.mvvmtodo.model.TodoFolder
import com.ankit.mvvmtodo.model.TodoRecord
import com.ankit.mvvmtodo.ui.RepositoryTodo
import com.ankit.mvvmtodo.util.debugger
import kotlinx.coroutines.launch
class TodoViewModel(private val todoRepository: RepositoryTodo):ViewModel() {




    fun passingFolderID(uid: Int):LiveData<MutableList<TodoRecord>> {
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
    fun allFolderList():LiveData<MutableList<TodoFolder>>{
        return todoRepository.getAllFolders()
    }








    fun searchNotes(input: String?):LiveData<MutableList<TodoRecord>> {
        debugger("SEARCH-?>>>$input")
        return     todoRepository.searchNotesRepo(input)
    }
    fun searchFolders(input: String?):LiveData<MutableList<TodoFolder>>{
        return  todoRepository.searchFolderRepo(input)
    }


}


