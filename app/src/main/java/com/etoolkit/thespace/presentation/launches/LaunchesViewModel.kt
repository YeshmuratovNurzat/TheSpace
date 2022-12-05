package com.etoolkit.thespace.presentation.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.data.network.launches.LaunchesApiFactory
import com.etoolkit.data.network.launches.LaunchesRepositoryImpl
import com.etoolkit.domain.launches.model.ResultLaunches
import com.etoolkit.domain.launches.usecase.GetLaunchesPastUseCase
import com.etoolkit.domain.launches.usecase.GetLaunchesSearchUseCase
import com.etoolkit.domain.launches.usecase.GetLaunchesUpcomingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class LaunchesViewModel : ViewModel() {

    private val repository = LaunchesRepositoryImpl(LaunchesApiFactory.launchesApiService)

    private val getLaunchesPastUseCase = GetLaunchesPastUseCase(repository)
    private val getLaunchesUpcomingUseCase = GetLaunchesUpcomingUseCase(repository)
    private val getLaunchesSearchUseCase = GetLaunchesSearchUseCase(repository)

    private val _getLaunchesPastResult = MutableLiveData<ResultLaunches>()
    val getLaunchesPastResult : LiveData<ResultLaunches> get() = _getLaunchesPastResult

    private val _getLaunchesUpcomingResult = MutableLiveData<ResultLaunches>()
    val getLaunchesUpcomingResult : LiveData<ResultLaunches> get() = _getLaunchesUpcomingResult

    private val _getLaunchesSearchResult = MutableLiveData<ResultLaunches>()
    val getLaunchesSearchResult : LiveData<ResultLaunches> get() = _getLaunchesSearchResult


    fun getLaunchesPast(){
        viewModelScope.launch {
            _getLaunchesPastResult.value = getLaunchesPastUseCase.invoke()
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