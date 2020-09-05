package com.ankit.mvvmtodo.DataBase

import com.ankit.mvvmtodo.TodoFolder
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.ankit.mvvmtodo.TodoRecord

@Dao
interface TodoDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveTodoDTO(todoRecord: TodoRecord)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun saveTodoInFolder(todoFolder: TodoFolder)

@Update
suspend fun savePinTodoRecord(vararg todoRecord: TodoRecord)
    @Update
    suspend fun updateTodoDTO(vararg todoRecord: TodoRecord)

    @Update
    suspend fun updateFolder(vararg todoFolder: TodoFolder)
    @Delete
    suspend fun deleteTodoDTO(todoRecord: TodoRecord)

    @Delete
    suspend fun deleteFolder(todoFolder: TodoFolder)

    @Query("SELECT * FROM TodoFolder ORDER BY folderId DESC")
    fun getFolders():LiveData<List<TodoFolder>>


    @Query("SELECT * FROM TodoRecord  WHERE userCreatedFolderId LIKE :folderUID ORDER BY todoId DESC")

    fun getRecords(folderUID: Int?):LiveData<List<TodoRecord>>


    @Query("SELECT * FROM TodoRecord ORDER BY todoId DESC")
    fun getRecordsAll():LiveData<List<TodoRecord>>

    @Query("SELECT * FROM TodoRecord WHERE title LIKE :search")
    fun searchTodo(search:String?):MutableList<TodoRecord>


    @Query("SELECT * FROM TodoRecord WHERE title LIKE :search AND userCreatedFolderId=:uid")
    fun searchTodoOne(search:String?,uid:Int?):LiveData<List<TodoRecord>>

    @Query("SELECT * FROM TodoFolder WHERE folderName LIKE:folderName ORDER BY folderId DESC")
    fun searchFolder(folderName:String?):LiveData<List<TodoFolder>>

    @Query("SELECT * FROM TodoRecord WHERE password LIKE:pin")
  fun  getPinTodo(pin:String):Boolean

}