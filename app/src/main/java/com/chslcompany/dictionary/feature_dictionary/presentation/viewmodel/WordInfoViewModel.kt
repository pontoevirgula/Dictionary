package com.chslcompany.dictionary.feature_dictionary.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chslcompany.dictionary.core.util.Resource
import com.chslcompany.dictionary.feature_dictionary.domain.usecase.GetWordInfoUseCase
import com.chslcompany.dictionary.feature_dictionary.presentation.WordInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(val useCase: GetWordInfoUseCase) : ViewModel(){

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state : State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob : Job? = null

    fun onSearch(query : String){
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DELAY_MILLIS)
            useCase(query)
                .onEach { result ->
                    when(result) {
                        is Resource.Success ->{
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList() ,
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UiEvent.ShowSnackBar(
                                    result.message ?: UNKNOWN_ERROR
                                )
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }


    sealed class UiEvent{
        data class ShowSnackBar(val message: String) : UiEvent()
    }

    companion object{
        private const val DELAY_MILLIS = 500L
        private const val UNKNOWN_ERROR = "Erro desconhecido"
    }
}