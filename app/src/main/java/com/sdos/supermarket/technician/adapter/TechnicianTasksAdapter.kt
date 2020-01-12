package com.sdos.supermarket.technician.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sdos.supermarket.R
import com.sdos.supermarket.common.inflate
import com.sdos.supermarket.domain.model.Task
import kotlin.properties.Delegates

typealias Listener = (Task, Boolean) -> Unit

class TechnicianTasksAdapter(
    listItems: List<Task>,
    val listener: Listener
) : RecyclerView.Adapter<TechnicianTasksAdapter.ItemViewHolder>() {

    var items: List<Task> by Delegates.observable(listItems) { _, _, _ -> this.notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(parent.inflate(R.layout.item_task))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskDescription = itemView.findViewById(R.id.taskDescription) as TextView
        private val taskType = itemView.findViewById(R.id.taskType) as TextView
        private val taskTime = itemView.findViewById(R.id.taskTime) as TextView
        private val checkboxTaskCompleted =
            itemView.findViewById(R.id.checkboxTaskCompleted) as CheckBox

        fun bind(task: Task) {
            checkboxTaskCompleted.setOnCheckedChangeListener(null)

            taskDescription.text = task.description
            taskType.text = task.taskType
            taskTime.text = task.hours.toString()
            checkboxTaskCompleted.isChecked = task.completed

            checkboxTaskCompleted.setOnCheckedChangeListener { _, isChecked ->
                listener(items[adapterPosition], isChecked)
            }
        }
    }
}
