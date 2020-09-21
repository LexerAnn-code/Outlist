package com.ankit.mvvmtodo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ankit.mvvmtodo.model.TodoFolder
import kotlinx.android.parcel.Parcelize


@Entity(foreignKeys = [
    ForeignKey(entity = TodoFolder::class,parentColumns = arrayOf("folderId"),
        childColumns = arrayOf("userCreatedFolderId"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)
])
@Parcelize
data class TodoRecord(
    @PrimaryKey(autoGenerate = true)
    var todoId:Int?,
    val userCreatedFolderId:Int?,
    val title:String,
    val content:String,
    val date:String?=null,
val password:String?=null
):Parcelable

