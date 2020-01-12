package com.sdos.supermarket.product

import androidx.lifecycle.*
import com.sdos.supermarket.common.Either
import com.sdos.supermarket.domain.interactor.PeachesInteractor
import com.sdos.supermarket.domain.model.Product
import kotlinx.coroutines.launch

class ProductViewModelFactory(private val viewModel: ProductViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = viewModel as T
}

class ProductViewModel(
    private val peachesInteractor: PeachesInteractor
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _error: MutableLiveData<Boolean> = MutableLiveData()
    val error: LiveData<Boolean> = _error

    init {
        viewModelScope.launch {
            _productList.value =
                peachesInteractor(Unit).let {
                    if (it is Either.Success) {
                        it.content
                    } else {
                        _error.value = true
                        null
                    }

                }
        }
    }

}
