package com.sdos.supermarket.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.sdos.supermarket.R
import com.sdos.supermarket.common.BaseFragment
import com.sdos.supermarket.common.CustomDialogView
import com.sdos.supermarket.domain.DialogBody
import com.sdos.supermarket.product.adapter.ProductAdapter
import kotlinx.android.synthetic.main.fragment_product.*
import org.koin.core.inject

class ProductFragment : BaseFragment() {

    lateinit var adapter: ProductAdapter

    private val factory by inject<ProductViewModelFactory>()
    private val viewModel by lazy {
        ViewModelProvider(this, factory)
            .get(ProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_product, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.productList.observe(viewLifecycleOwner, Observer { products ->
            products?.let {
                adapter.items = products
            }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            if (error) {
                context?.let {
                    CustomDialogView(
                        context = it,
                        dialogBody = DialogBody(
                            title = getString(R.string.error_network_title),
                            body = getString(R.string.error_network_message)
                        )
                    ).show()
                }
            }
        })

    }

    private fun initViews() {
        adapter = ProductAdapter(listOf())

        productRV.adapter = adapter
        productRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }
}