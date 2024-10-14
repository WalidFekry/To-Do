package com.walid.todo.database.model

import android.provider.ContactsContract.Data
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo
    var todoName: String? = null,
    @ColumnInfo
    var todoDescription: String? = null,
    @ColumnInfo
    var todoDate: Long? = null,
    @ColumnInfo
    var isDone: Boolean? = false,
):java.io.Serializable
