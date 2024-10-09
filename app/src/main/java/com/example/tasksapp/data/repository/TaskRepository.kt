package com.example.tasksapp.data.repository

import androidx.lifecycle.LiveData
import com.example.tasksapp.data.local.dao.TaskDao
import com.example.tasksapp.data.model.Task

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun insertTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun insertTasks(tasks: List<Task>) {
        taskDao.insertAll(tasks)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    fun getTask(taskId: Long): LiveData<Task> {
        return taskDao.get(taskId)
    }

    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAll()
    }
}