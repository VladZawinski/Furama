package non.shahad.furama.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import non.shahad.furama.adapters.Room

class RoomViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<RoomUiState>(RoomUiState.Idle)
    val uiState: StateFlow<RoomUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.value = RoomUiState.Loading
            delay(500)
            _uiState.value = RoomUiState.Success(mocks())
        }
    }


    private fun mocks() = listOf(
        Room("No more Adapters"),
        Room("No more Adapters"),
        Room("No more Adapters")
    )
}

sealed class RoomUiState {
    data class Success(val rooms: List<Room>): RoomUiState()
    data class Error(val exception: Throwable): RoomUiState()
    object Loading: RoomUiState()
    object Idle: RoomUiState()
}