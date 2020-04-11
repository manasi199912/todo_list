package com.example.dashboard

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dashboard.Task_subTask.Todo
import java.util.*

class DBhandler(val context:Context) :SQLiteOpenHelper(context, DB_name,null, DB_version)
{
    override fun onCreate(p0: SQLiteDatabase) {
        val createToDoTable:String="create table ToDo("+
                "$Col_id integer primary key autoincrement,"+
                "$Col_Created_At datetime default current_timestamp"+
                "$Col_name varchar);"
        val createToDoItemTable:String= "create table TodoItem("+
                        "$Col_id primary key autoincrement,"+
                        "$Col_Created_At datetime default current_timestamp"+
                        "$Col_todo_id integer"+
                        "$Col_item_name varchar"+
                        "$Col_is_completed integer);"
        p0.execSQL(createToDoTable)
        p0.execSQL(createToDoItemTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            }
    fun addList(todo: Todo):Boolean{
        val db:SQLiteDatabase=writableDatabase
        val cv=ContentValues()
        cv.put(Col_name,todo.name)
        val result:Long=db.insert(Table_name,null,cv)
        return result !=(-1).toLong()
    }
    fun show():MutableList<Todo>{
        val result:MutableList<Todo> =ArrayList()
        val db:SQLiteDatabase=readableDatabase
        val queryresult:Cursor = db.rawQuery("select*from ${Table_name}",null)
        if(queryresult.moveToFirst())
            do {
                val todo=Todo()
                todo.id=queryresult.getLong(queryresult.getColumnIndex(Col_id))
                todo.name=queryresult.getString(queryresult.getColumnIndex(Col_name))
                result.add(todo)
            }while(queryresult.moveToNext())
        return result
    }
}
