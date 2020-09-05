package com.ankit.mvvmtodo.KoinInjection

import android.app.Application
import androidx.room.Room
import com.ankit.mvvmtodo.DataBase.TodoDao
import com.ankit.mvvmtodo.DataBase.AppDataBase
import com.ankit.mvvmtodo.TodoViewModel
import com.ankit.mvvmtodo.UI.RepositoryTodo

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { TodoViewModel(get ()) }
}

val databaseModule=module{
    fun provideDataBase(application: Application): AppDataBase {
        return Room.databaseBuilder(application,
            AppDataBase::class.java,"todo.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    fun provideDao(dataBase: AppDataBase): TodoDao {
        return dataBase.todoDao
    }
    single { provideDataBase(androidApplication()) }
    single { provideDao(get ()) }

}
val repositoryModule= module {
    fun provideUserRepository(dao: TodoDao): RepositoryTodo {
        return RepositoryTodo(dao)
    }
        single { provideUserRepository(get())}
}