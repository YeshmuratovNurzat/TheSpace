package com.etoolkit.thespace.presentation.agencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.data.network.agencies.AgenciesApiFactory
import com.etoolkit.data.network.agencies.AgenciesRepositoryImpl
import com.etoolkit.domain.agencies.model.ResultAgencies
import com.etoolkit.domain.agencies.usecase.GetAgenciesSearchUseCase
import com.etoolkit.domain.agencies.usecase.GetAgenciesUseCase
import kotlinx.coroutines.launch

class AgenciesViewModel : ViewModel() {

    private val repository = AgenciesRepositoryImpl(AgenciesApiFactory.agenciesApiService)

    private val getAgenciesUseCase = GetAgenciesUseCase(repository)
    private val getAgenciesSearchUseCase = GetAgenciesSearchUseCase(repository)

    private val _getAgenciesResult = MutableLiveData<ResultAgencies>()
    val getAgenciesResult : LiveData<ResultAgencies> get() = _getAgenciesResult

    private val _getAgenciesSearchResult = MutableLiveData<ResultAgencies>()
    val getAgenciesSearchResult : LiveData<ResultAgencies> get() = _getAgenciesSearchResult

    fun getAgencies(){
        viewModelScope.launch {
            _getAgenciesResult.value = getAgenciesUseCase.invoke()
        }
    }

    fun getAgenciesSearch(search : String){
        viewModelScope.launch {
            _getAgenciesSearchResult.value = getAgenciesSearchUseCase.invoke(search)
        }
    }
}