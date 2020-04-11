package com.example.dashboard.Task_subTask

import java.util.*
class Todo
{
    lateinit var getName: Any
    var id:Long=-1
    var name=getName;
    var createdAt=""
    var items:MutableList<TodoItem> = ArrayList()
}
