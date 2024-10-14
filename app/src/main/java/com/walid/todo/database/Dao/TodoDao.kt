package com.walid.todo.database.Dao

import androidx.room.*
import com.walid.todo.database.model.Todo

@Dao
interface TodoDao {

    @Insert
    fun insertTodo(todo : Todo)

    @Delete
    fun deleteTodo(todo : Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("SELECT * FROM Todo")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM Todo where todoDate = :date order by id DESC")
    fun selectAllTodoByDate(date:Long): List<Todo>

}