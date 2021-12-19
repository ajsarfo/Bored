package com.sarftec.bored.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sarftec.bored.tools.extra.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

): ViewModel() {

    private val _screenState = MutableLiveData<Event<ScreenState>>()
    val screenState: LiveData<Event<ScreenState>>
    get() = _screenState

    fun getTask() {
        _screenState.value = Event(ScreenState.GetTask)
    }

    sealed class ScreenState {
       object GetTask : ScreenState()
    }
}