package com.etoolkit.thespace.presentation.astronauts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.data.network.astronauts.AstronautsApiFactory
import com.etoolkit.data.network.astronauts.AstronautsRepositoryImpl
import com.etoolkit.domain.astronauts.model.ResultAstronauts
import com.etoolkit.domain.astronauts.usecase.GetAstronautsOffsetUseCase
import com.etoolkit.domain.astronauts.usecase.GetAstronautsSearchUseCase
import com.etoolkit.domain.astronauts.usecase.GetAstronautsUseCase
import kotlinx.coroutines.launch

class AstronautsViewModel : ViewModel() {

    private val repository = AstronautsRepositoryImpl(AstronautsApiFactory.astronautsApiService)

    private val getAstronautsUseCase = GetAstronautsUseCase(repository)
    private val getAstronautsOffsetUseCase = GetAstronautsOffsetUseCase(repository)
    private val getAstronautsSearchUseCase = GetAstronautsSearchUseCase(repository)

    private val _getAstronautsResult = MutableLiveData<ResultAstronauts>()
    val getAstronautsResult : LiveData<ResultAstronauts> get() = _getAstronautsResult

    private val _getAstronautsOffsetResult = MutableLiveData<ResultAstronauts>()
    val getAstronautsOffsetResult : LiveData<ResultAstronauts> get() = _getAstronautsOffsetResult

    private val _getAstronautsSearchResult = MutableLiveData<ResultAstronauts>()
    val getAstronautsSearchResult : LiveData<ResultAstronauts> get() = _getAstronautsSearchResult

    fun getAstronauts() {
        viewModelScope.launch {
            _getAstronautsResult.value = getAstronautsUseCase.invoke()
        }
    }

    fun getAstronautsOffset(offset : String){
        viewModelScope.launch {
            _getAstronautsOffsetResult.value = getAstronautsOffsetUseCase.invoke(offset)
        }
    }

    fun getAstronautSearch(search : String){
        viewModelScope.launch {
            _getAstronautsSearchResult.value = getAstronautsSearchUseCase.invoke(search)
        }
    }
}