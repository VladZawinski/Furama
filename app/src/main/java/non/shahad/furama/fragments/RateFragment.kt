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
import non.shahad.furama.adapters.Rate
import non.shahad.furama.adapters.rateItem
import non.shahad.furama.databinding.FragmentRateBinding
import non.shahad.furama.viewmodels.RateUiState
import non.shahad.furama.viewmodels.RateViewModel

class RateFragment(
    override val layoutRes: Int = R.layout.fragment_rate
) : ViewBindingFragment<FragmentRateBinding>(){

    private val viewModel by viewModels<RateViewModel>()

    private val delegate =
        AsyncListDifferDelegationAdapter(
            RateDiffItemCallback,
            rateItem()
        )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.rateRv.adapter = delegate
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collectLatest {
                    when(it) {
                        is RateUiState.Error -> {
                            // No need to handle, I trust the system :D
                        }
                        RateUiState.Idle -> {
                            // Do Nothing
                        }
                        RateUiState.Loading -> {
                            // Do something when loading
                        }
                        is RateUiState.Success -> {
                            Log.d("RateFragment", "onViewCreated: ${it.rates}")
                            delegate.items = it.rates
                        }
                    }
                }
            }
        }


    }

    object RateDiffItemCallback: DiffUtil.ItemCallback<Rate>() {
        override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
            return oldItem.name == newItem.name
        }

    }
}