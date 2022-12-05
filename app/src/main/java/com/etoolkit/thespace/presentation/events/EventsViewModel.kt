package com.etoolkit.thespace.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etoolkit.data.network.events.EventsApiFactory
import com.etoolkit.data.network.events.EventsRepositoryImpl
import com.etoolkit.domain.events.model.ResultEvents
import com.etoolkit.domain.events.usecase.GetEventsSearchUseCase
import com.etoolkit.domain.events.usecase.GetEventsUseCase
import kotlinx.coroutines.launch

class EventsViewModel : ViewModel() {

    private val repository = EventsRepositoryImpl(EventsApiFactory.eventsApiService)

    private val getEventsUseCase = GetEventsUseCase(repository)
    private val getEventsSearchUseCase = GetEventsSearchUseCase(repository)

    private val _getEventsResult = MutableLiveData<ResultEvents>()
    val getEventsResult : LiveData<ResultEvents> get() = _getEventsResult

    private val _getEventsSearchResult = MutableLiveData<ResultEvents>()
    val getEventsSearchResult : LiveData<ResultEvents> get() = _getEventsSearchResult


    fun getEvents(){
        viewModelScope.launch {
            _getEventsResult.value = getEventsUseCase.invoke()
        }
    }

    fun getEventsSearch(search : String){
        viewModelScope.launch {
            _getEventsSearchResult.value = getEventsSearchUseCase.invoke(search)
        }
    }

}