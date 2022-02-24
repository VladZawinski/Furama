package non.shahad.furama.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import non.shahad.furama.adapters.Rate


class RateViewModel: ViewModel() {

    private val _uiState = MutableStateFlow<RateUiState>(RateUiState.Idle)
    val uiState: StateFlow<RateUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = RateUiState.Loading
            delay(500)
            _uiState.value = RateUiState.Success(mocks())
        }
    }


    private fun mocks() = listOf(
        Rate("No more Adapters"),
        Rate("No more Adapters"),
        Rate("No more Adapters")
    )
}

sealed class RateUiState {
    data class Success(val rates: List<Rate>): RateUiState()
    data class Error(val exception: Throwable): RateUiState()
    object Loading: RateUiState()
    object Idle: RateUiState()
}