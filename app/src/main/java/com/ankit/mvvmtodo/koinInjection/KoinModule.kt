package com.ankit.mvvmtodo.koinInjection

import android.app.Application
import androidx.room.Room
import com.ankit.mvvmtodo.database.TodoDao
import com.ankit.mvvmtodo.database.AppDataBase
import com.ankit.mvvmtodo.viewmodel.TodoViewModel
import com.ankit.mvvmtodo.ui.RepositoryTodo

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { TodoViewModel(get()) }
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