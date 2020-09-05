package com.ankit.mvvmtodo.Util

import java.text.SimpleDateFormat
import java.util.*

fun dateFormatter():String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}