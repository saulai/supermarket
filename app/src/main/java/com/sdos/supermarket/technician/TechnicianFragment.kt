package com.sdos.supermarket.technician

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseFragment
import com.sdos.supermarket.technician.adapter.TechnicianTasksAdapter
import kotlinx.android.synthetic.main.fragment_technician.*
import org.koin.core.inject

class TechnicianFragment : BaseFragment() {

    private val factory by inject<TechnicianViewModelFactory>()
    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(TechnicianViewModel::class.java)
    }

    lateinit var adapter: TechnicianTasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_technician, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeViewModel()
        navArgs<TechnicianFragmentArgs>().value.userId?.let {
            viewModel.onViewReady(it)
        }
    }

    private fun subscribeViewModel() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            adapter.items = tasks
        })

        viewModel.user.observe(viewLifecycleOwner, Observer {
            nameTV.text = it.firstName
            capabilitiesTV.text = it.capabilities.toString()
        })
    }

    private fun initViews() {
        adapter = TechnicianTasksAdapter(listOf()) { completedTask, taskCompletion ->
            viewModel.onTaskCompleted(completedTask, taskCompletion)
        }

        tasksRV.adapter = adapter
        tasksRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}