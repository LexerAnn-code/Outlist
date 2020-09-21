package com.ankit.mvvmtodo.database

import com.ankit.mvvmtodo.model.TodoRecord
import com.ankit.mvvmtodo.model.TodoFolder
import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TodoRecord::class, TodoFolder::class],version = 14,exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
abstract val todoDao: TodoDao
}