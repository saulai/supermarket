package com.sdos.supermarket.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseFragment
import com.sdos.supermarket.common.CustomDialogView
import com.sdos.supermarket.common.navigate
import com.sdos.supermarket.domain.DialogBody
import com.sdos.supermarket.domain.enum.UserType
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.core.inject

class LoginFragment : BaseFragment() {

    private val factory by inject<LoginViewModelFactory>()

    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        initViews()
    }

    private fun initViews() {
        loginButton.setOnClickListener {

            viewModel.onLoginClicked(
                userIET.editableText.toString().trim(),
                passwordIET.editableText.toString().trim()
            )

        }
    }

    private fun subscribeViewModel() {
        viewModel.user.observe(viewLifecycleOwner, Observer { user ->
            user?.let {
                when (UserType.valueOf(it.userType)) {
                    UserType.MANAGER -> navigate(LoginFragmentDirections.actionLoginDestToManagerDest())
                    UserType.TECHNICIAN -> navigate(
                        LoginFragmentDirections.actionLoginDestToTechnicianDest(
                            it.userId
                        )
                    )
                }
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            context?.let {
                CustomDialogView(
                    context = it,
                    dialogBody = DialogBody(
                        title = getString(R.string.error_login_title),
                        body = getString(R.string.error_login_message)
                    )
                ).show()
            }
        })
    }
}