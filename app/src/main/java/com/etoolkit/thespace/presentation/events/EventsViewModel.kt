package com.etoolkit.thespace.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.thespace.data.network.events.EventsApiFactory
import com.etoolkit.thespace.data.network.events.EventsRepositoryImpl
import com.etoolkit.thespace.domain.events.model.ResultEvents
import com.etoolkit.thespace.domain.events.usecase.GetEventsUseCase
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    private val repository = EventsRepositoryImpl(EventsApiFactory.eventsApiService)

    private val getEventsUseCase = GetEventsUseCase(repository)

    private val _getEventsResult = MutableLiveData<ResultEvents>()
    val getEventsResult : LiveData<ResultEvents> get() = _getEventsResult

    fun getEvents(){
        viewModelScope.launch {
            _getEventsResult.value = getEventsUseCase.invoke()
        }
    }

}