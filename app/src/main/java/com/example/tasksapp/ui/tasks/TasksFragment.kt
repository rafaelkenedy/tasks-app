package com.example.tasksapp.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapp.data.local.database.TaskDatabase
import com.example.tasksapp.data.repository.TaskRepository
import com.example.tasksapp.databinding.FragmentTasksBinding
import com.example.tasksapp.ui.tasks.adapter.TaskItemAdapter
import com.example.tasksapp.ui.tasks.viewmodel.TasksViewModel
import com.example.tasksapp.ui.tasks.viewmodel.ViewModelFactory


class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val repository = TaskRepository(dao)

        val viewModel = ViewModelProvider(
            this, ViewModelFactory.factory<TasksViewModel>(repository)
        )[TasksViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter { taskId ->
//            viewModel.onTaskClicked(taskId)
//            val action = TasksFragmentDirections
//                .actionTasksFragmentToEditTaskFragment(taskId)
//            this.findNavController().navigate(action)
//            Toast.makeText(context, "Clicked task  $taskId", Toast.LENGTH_SHORT).show()
            viewModel.onTaskClicked(taskId)
        }
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            tasks?.let {
                //adapter.data = tasks
                adapter.submitList(tasks)
            }
        })

        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer { taskId ->
            taskId?.let {
                val action = TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(taskId)
                this.findNavController().navigate(action)
                viewModel.onTaskNavigated()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
