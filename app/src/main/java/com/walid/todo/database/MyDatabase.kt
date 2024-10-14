package com.walid.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.walid.todo.database.Dao.TodoDao
import com.walid.todo.database.model.Todo


@Database(entities = [Todo::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun todoDoa(): TodoDao

    companion object {

        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java, "Todo"
                ).allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }

    }


}