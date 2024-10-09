package com.example.tasksapp.ui.tasks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasksapp.data.model.Task
import com.example.tasksapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class TasksViewModel(private val repository: TaskRepository) : ViewModel() {

    var newTaskName = ""

    val tasks = repository.getAllTasks()
    private val _navigateToTask = MutableLiveData<Long?>()

    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            repository.insertTask(task)
        }
    }

    fun onTaskClicked(taskId: Long) {
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated() {
        _navigateToTask.value = null
    }

//    val taskString = tasks.map { tasks ->
//        formatTasks(tasks)
//    }

//    private fun formatTasks(tasks: List<Task>): String {
//        return tasks.joinToString(separator = "\n") { formatTask(it) }
//    }

//    private fun formatTask(task: Task): String {
//        return "ID: ${task.taskId}\nName: ${task.taskName}\nComplete: ${task.taskDone}\n"
//    }

//    companion object {
//        private inline fun <reified VM : ViewModel> viewModelFactory(crossinline creator: () -> VM): ViewModelProvider.Factory =
//            object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T =
//                    if (modelClass.isAssignableFrom(VM::class.java)) {
//                        creator() as T
//                    } else {
//                        throw IllegalArgumentException(UNKNOWN_VIEWMODEL_CLASS)
//                    }
//            }
//
//        fun factory(repository: TaskRepository): ViewModelProvider.Factory =
//            viewModelFactory { TasksViewModel(repository) }
//    }
}
