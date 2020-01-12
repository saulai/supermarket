package com.sdos.supermarket.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseFragment
import com.sdos.supermarket.common.CustomDialogView
import com.sdos.supermarket.common.showSnack
import com.sdos.supermarket.domain.DialogBody
import com.sdos.supermarket.domain.model.BaseTask
import kotlinx.android.synthetic.main.fragment_manager.*
import org.koin.core.inject


class ManagerFragment : BaseFragment() {

    private val factory by inject<ManagerViewModelFactory>()
    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(ManagerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_manager, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        initViews()
    }

    private fun subscribeViewModel() {
        viewModel.baseTasks.observe(viewLifecycleOwner, Observer { baseTaskList ->
            context?.let {
                if (!baseTaskList.isNullOrEmpty()) {
                    val adapter = ArrayAdapter(it, R.layout.spinner_item, baseTaskList)
                    adapter.setDropDownViewResource(R.layout.spinner_item)
                    taskTypeSpinner.adapter = adapter
                }
            }
        })

        viewModel.taskAsignedToUser.observe(viewLifecycleOwner, Observer { user ->
            if (!user.isNullOrBlank()) {
                taskTypeSpinner.setSelection(0)
                taskDescriptionIET.editableText.clear()
                taskDurationIET.editableText.clear()

                showSnack("${getString(R.string.task_successfully_asigned)} $user")
            }
        })
    }

    private fun initViews() {
        addTaksButton.setOnClickListener {
            if (taskDescriptionIET.editableText.isNotBlank() && taskDurationIET.editableText.isNotBlank()) {
                viewModel.onAddTaskClicked(
                    taskTypeSpinner.selectedItem as BaseTask,
                    taskDescriptionIET.editableText.toString(),
                    taskDurationIET.editableText.toString()
                )
            } else {
                context?.let {
                    CustomDialogView(
                        context = it,
                        dialogBody = DialogBody(
                            title = getString(R.string.error_add_task_title),
                            body = getString(R.string.error_add_task_message)
                        )
                    ).show()
                }
            }
        }
    }
}