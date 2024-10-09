package com.example.tasksapp.ui.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
object ViewModelFactory {

    inline fun <reified VM : ViewModel> factory(vararg params: Any): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return VM::class.constructors.first().call(*params) as T
            }
        }
}
