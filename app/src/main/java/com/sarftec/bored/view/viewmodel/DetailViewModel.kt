package com.sarftec.bored.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarftec.bored.domain.model.Task
import com.sarftec.bored.domain.usecase.GetRandomTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRandomTask: GetRandomTask
): ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState>
        get() = _screenState

    fun getTask() {
        viewModelScope.launch {
            _screenState.value = ScreenState.Loading
            _screenState.value = getRandomTask().result.let {
                if(it.isError()) ScreenState.Error(it.message!!)
                else ScreenState.IncomingTask(it.data!!)
            }
        }
    }

    sealed class ScreenState {
        class IncomingTask(val task: Task) : ScreenState()
        class Error(val message: String) : ScreenState()
        object Loading : ScreenState()
    }
}