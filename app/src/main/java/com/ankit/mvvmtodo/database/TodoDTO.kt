package com.ankit.mvvmtodo.database

import com.ankit.mvvmtodo.model.TodoFolder
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.*
import com.ankit.mvvmtodo.model.TodoRecord

@Dao
interface TodoDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun saveTodoDTO(todoRecord: TodoRecord)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend  fun saveTodoInFolder(todoFolder: TodoFolder)
    @Query("SELECT * FROM TodoRecord WHERE userCreatedFolderId LIKE :folderUID ORDER BY todoId DESC")
    fun getRecordsAll(folderUID: Int?):LiveData<MutableList<TodoRecord>>


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
    fun getFolders():LiveData<MutableList<TodoFolder>>

//
//    @Query("SELECT * FROM TodoRecord  WHERE userCreatedFolderId LIKE :folderUID ORDER BY todoId DESC")
//
//    fun getRecords(folderUID: Int?):LiveData<MutableList<TodoRecord>>
@Query("SELECT * FROM TodoRecord  WHERE userCreatedFolderId LIKE :folderUID ORDER BY todoId DESC")
fun getRecords(folderUID: Int?):LiveData<MutableList<TodoRecord>>



//    @Query("SELECT * FROM TodoRecord WHERE title LIKE :search")
//    fun searchTodo(search:String?):LiveData<MutableList<TodoRecord>>


    @Query("SELECT * FROM TodoRecord WHERE  userCreatedFolderId=:uid  AND title LIKE :search")
    fun  searchTodoOne(uid:Int?,search:String?):LiveData<MutableList<TodoRecord>>

    @Query("SELECT * FROM TodoFolder WHERE folderName LIKE:folderName ORDER BY folderId DESC")
    fun searchFolder(folderName:String?):LiveData<MutableList<TodoFolder>>

    @Query("SELECT * FROM TodoRecord WHERE password LIKE:pin")
  fun  getPinTodo(pin:String):Boolean

}