package com.ankit.mvvmtodo.Util

import android.app.Activity
import android.widget.Toast
var checkView=0
fun debugger(msg:Any?)=println("Message->>${msg.toString()}")

fun Activity.toastMessage(msg:String?){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}
fun Activity.goBack(){
    onBackPressed()
}