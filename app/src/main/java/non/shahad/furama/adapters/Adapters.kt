package non.shahad.furama.adapters

import androidx.databinding.DataBindingUtil
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import non.shahad.furama.R
import non.shahad.furama.databinding.ViewholderRateBinding
import non.shahad.furama.databinding.ViewholderRoomBinding
import java.util.*

fun roomItem() = adapterDelegateViewBinding<Room, Room, ViewholderRoomBinding>(
    { inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_room,root,false) }
){
    bind {

    }
}

fun rateItem() = adapterDelegateViewBinding<Rate, Rate, ViewholderRateBinding>(
    { inflater,root -> DataBindingUtil.inflate(inflater, R.layout.viewholder_rate,root,false) }
){
    bind {

    }
}


data class Room(
    val name: String,
    val id: String = UUID.randomUUID().toString()
)

data class Rate(
    val name: String,
    val id: String = UUID.randomUUID().toString()
)