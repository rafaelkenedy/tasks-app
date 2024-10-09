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
import com.example.tasksapp.databinding.FragmentEditTaskBinding
import com.example.tasksapp.ui.tasks.viewmodel.EditTaskViewModel
import com.example.tasksapp.ui.tasks.viewmodel.ViewModelFactory


class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root

        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val repository = TaskRepository(dao)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.factory<EditTaskViewModel>(taskId, repository)
        )[EditTaskViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                val action = EditTaskFragmentDirections.actionEditTaskFragmentToTasksFragment()
                this.findNavController().navigate(action)
                viewModel.onNavigatedToList()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}