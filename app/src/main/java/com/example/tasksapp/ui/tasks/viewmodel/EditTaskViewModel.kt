package com.example.tasksapp.ui.tasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val repository: TaskRepository) : ViewModel() {
    val task = repository.getTask(taskId)
    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateTask() {
        viewModelScope.launch {
            task.value?.let {
                repository.updateTask(it)
                _navigateToList.value = true
            }
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            task.value?.let {
                repository.deleteTask(it)
            }
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }

}
