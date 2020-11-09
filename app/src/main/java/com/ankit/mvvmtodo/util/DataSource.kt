//package com.ankit.mvvmtodo.util
//
//import androidx.paging.PagingSource
//import com.ankit.mvvmtodo.database.TodoDao
//import com.ankit.mvvmtodo.model.TodoRecord
//import java.io.IOException
//import java.net.HttpRetryException
//
//class DataSource(private val dao: TodoDao,private val uuid:Int): PagingSource<Int, TodoRecord>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TodoRecord> {
//        val position = params.key ?: 1
//        debugger("POSITION $position")
//        debugger("NULLLIST$")
//        return try {
//            val response=dao.getRecords(uuid)
//
//
//            LoadResult.Page(
//                    data = response.,
//                    nextKey = if(response.isNotEmpty()) null else position+1,
//                    prevKey =  null
//
//                    )
//
//        }catch (exception:IOException){
//            return LoadResult.Error(exception)
//        }
//}
//}