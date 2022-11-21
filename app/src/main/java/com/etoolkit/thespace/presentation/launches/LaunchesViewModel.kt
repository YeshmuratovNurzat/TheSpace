package com.etoolkit.thespace.presentation.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.data.network.launches.LaunchesApiFactory
import com.etoolkit.data.network.launches.LaunchesRepositoryImpl
import com.etoolkit.domain.launches.model.ResultLaunches
import com.etoolkit.domain.launches.usecase.GetLaunchesSearchUseCase
import com.etoolkit.domain.launches.usecase.GetLaunchesUpcomingUseCase
import com.etoolkit.domain.launches.usecase.GetLaunchesUseCase
import kotlinx.coroutines.launch

class LaunchesViewModel : ViewModel() {

    private val repository = LaunchesRepositoryImpl(LaunchesApiFactory.launchesApiService)

    private val getLaunchesUseCase = GetLaunchesUseCase(repository)
    private val getLaunchesUpcomingUseCase = GetLaunchesUpcomingUseCase(repository)
    private val getLaunchesSearchUseCase = GetLaunchesSearchUseCase(repository)

    private val _getLaunchesResult = MutableLiveData<ResultLaunches>()
    val getLaunchesResult : LiveData<ResultLaunches> get() = _getLaunchesResult

    private val _getLaunchesUpcomingResult = MutableLiveData<ResultLaunches>()
    val getLaunchesUpcomingResult : LiveData<ResultLaunches> get() = _getLaunchesUpcomingResult

    private val _getLaunchesSearchResult = MutableLiveData<ResultLaunches>()
    val getLaunchesSearchResult : LiveData<ResultLaunches> get() = _getLaunchesSearchResult


    fun getLaunches(){
        viewModelScope.launch {
            _getLaunchesResult.value = getLaunchesUseCase.invoke()
        }
    }

    fun getLaunchesUpcoming(){
        viewModelScope.launch {
            _getLaunchesUpcomingResult.value = getLaunchesUpcomingUseCase.invoke()
        }
    }

    fun getLaunchesSearch(search : String){
        viewModelScope.launch {
            _getLaunchesSearchResult.value = getLaunchesSearchUseCase.invoke(search)
        }
    }

}