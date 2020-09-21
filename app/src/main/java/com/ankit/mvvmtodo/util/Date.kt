package com.ankit.mvvmtodo.util

import java.text.SimpleDateFormat
import java.util.*

fun dateFormatter():String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
}