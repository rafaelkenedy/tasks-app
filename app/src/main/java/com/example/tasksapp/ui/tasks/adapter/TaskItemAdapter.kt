package com.example.tasksapp.ui.tasks.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksapp.R
import com.example.tasksapp.data.model.Task
import com.example.tasksapp.databinding.TaskItemBinding

class TaskItemAdapter(private val clickListener: (taskId: Long) -> Unit) :
    ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

//    var data = listOf<Task>()
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }

//    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
//        val item = data[position]
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    //    class TaskItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
    class TaskItemViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {

//        val taskName = rootView.findViewById<TextView>(R.id.task_name)
//        val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)

        fun bind(item: Task, clickListener: (taskId: Long) -> Unit) {
//            taskName.text = item.taskName
//            taskDone.isChecked = item.taskDone
            binding.task = item
            binding.root.setOnClickListener {
                clickListener(item.taskId)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
//                val view = layoutInflater
//                    .inflate(R.layout.task_item, parent, false) as CardView
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }
    }

    class TaskDiffItemCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            (oldItem.taskId == newItem.taskId)

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            (oldItem == newItem)
    }
}