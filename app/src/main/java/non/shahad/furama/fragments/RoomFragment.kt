package non.shahad.furama.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import non.shahad.furama.R
import non.shahad.furama.adapters.Room
import non.shahad.furama.adapters.roomItem
import non.shahad.furama.databinding.FragmentRoomBinding
import non.shahad.furama.viewmodels.RoomUiState
import non.shahad.furama.viewmodels.RoomViewModel

class RoomFragment(
    override val layoutRes: Int = R.layout.fragment_room
) : ViewBindingFragment<FragmentRoomBinding>(){

    private val viewModel by viewModels<RoomViewModel>()

    private val delegate = AsyncListDifferDelegationAdapter(
        RoomDiffItemCallback,
        roomItem()
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.roomRv.adapter = delegate

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collectLatest {
                    when(it){
                        is RoomUiState.Error -> {

                        }
                        RoomUiState.Idle -> {

                        }
                        RoomUiState.Loading -> {
                            // Show some loading spinner or smth
                        }
                        is RoomUiState.Success -> {
                            delegate.items = it.rooms
                        }
                    }
                }
            }
        }


    }

    object RoomDiffItemCallback: DiffUtil.ItemCallback<Room>() {
        override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
            return oldItem.name == newItem.name
        }

    }
}