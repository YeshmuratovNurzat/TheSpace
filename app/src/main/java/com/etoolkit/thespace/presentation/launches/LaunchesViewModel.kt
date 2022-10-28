package com.etoolkit.thespace.presentation.launches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.thespace.data.network.launches.LaunchesApiFactory
import com.etoolkit.thespace.data.network.launches.LaunchesRepositoryImpl
import com.etoolkit.thespace.domain.launches.model.ResultLaunches
import com.etoolkit.thespace.domain.launches.usecase.GetLaunchesUseCase
import kotlinx.coroutines.launch

class LaunchesViewModel : ViewModel() {

    private val repository = LaunchesRepositoryImpl(LaunchesApiFactory.launchesApiService)

    private val getLaunchesUseCase = GetLaunchesUseCase(repository)

    private val _getLaunchesResult = MutableLiveData<ResultLaunches>()
    val getLaunchesResult : LiveData<ResultLaunches> get() = _getLaunchesResult

    fun getLaunches(){
        viewModelScope.launch {
            _getLaunchesResult.value = getLaunchesUseCase.invoke()
        }
    }
}