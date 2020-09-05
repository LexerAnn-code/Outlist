package com.ankit.mvvmtodo.DataBase

import com.ankit.mvvmtodo.TodoRecord
import com.ankit.mvvmtodo.TodoFolder
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TodoRecord::class, TodoFolder::class],version = 14,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
abstract val todoDao: TodoDao
}