package com.etoolkit.thespace.presentation.agencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.thespace.data.network.agencies.AgenciesApiFactory
import com.etoolkit.thespace.data.network.agencies.AgenciesRepositoryImpl
import com.etoolkit.thespace.domain.agencies.model.ResultAgencies
import com.etoolkit.thespace.domain.agencies.usecase.GetAgenciesUseCase
import kotlinx.coroutines.launch

class AgenciesViewModel : ViewModel() {

    private val repository = AgenciesRepositoryImpl(AgenciesApiFactory.agenciesApiService)

    private val getAgenciesUseCase = GetAgenciesUseCase(repository)

    private val _getAgenciesResult = MutableLiveData<ResultAgencies>()
    val getAgenciesResult : LiveData<ResultAgencies> get() = _getAgenciesResult

    fun getAgencies(){
        viewModelScope.launch {
            _getAgenciesResult.value = getAgenciesUseCase.invoke()
        }
    }
}