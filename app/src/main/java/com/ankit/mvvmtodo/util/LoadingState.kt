package com.ankit.mvvmtodo.util

import androidx.lifecycle.LiveData

data class LoadingState(val status:Status,val msg:String?=null, val submity: LiveData<String>?=null) {
companion object{
    val LOADED=LoadingState(Status.SUCCESS)
    val LOADING=LoadingState(Status.RUNNING)
    fun error(msg:String?)=LoadingState(Status.FAILED,msg)
    fun success(submit:LiveData<String>)=LoadingState(Status.SUBMITSUCCESS,submity = submit)
}

enum class Status{
    RUNNING,
    SUCCESS,
    FAILED,
    SUBMITSUCCESS
}
}