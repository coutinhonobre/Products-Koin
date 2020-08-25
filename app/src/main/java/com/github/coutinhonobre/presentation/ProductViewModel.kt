package com.github.coutinhonobre.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.coutinhonobre.util.LoadingState
import com.github.coutinhonobre.repository.ProductRepository
import com.github.coutinhonobre.model.General
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel(private val repo: ProductRepository): ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    private val _data = MutableLiveData<General>()
    val data: LiveData<General>
        get() = _data

    init {
        fetchData()
    }

    fun restart() = this.fetchData()

    private fun fetchData() {
        _loadingState.postValue(LoadingState.LOADING)
        repo.getAllProducts().enqueue(object : Callback<General>{
            override fun onResponse(call: Call<General>, response: Response<General>) {
                if (response.isSuccessful){
                    _data.postValue(response.body())
                    _loadingState.postValue(LoadingState.LOADED)
                }else
                    _loadingState.postValue(LoadingState.error(response.errorBody().toString()))
            }

            override fun onFailure(call: Call<General>, t: Throwable) {
                _loadingState.postValue(LoadingState.error(t.message))
            }

        })
    }



}