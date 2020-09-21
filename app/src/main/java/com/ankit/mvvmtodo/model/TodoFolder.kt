package com.ankit.mvvmtodo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class TodoFolder(
    @PrimaryKey(autoGenerate =true ) var  folderId: Int?=null,
    var folderName:String
):Parcelable
