package com.example.scoop.ui.adapter.component

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.scoop.R
import com.example.scoop.databinding.ChipItemBinding
import com.example.scoop.databinding.FullCardItemBinding
 import timber.log.Timber

@EpoxyModelClass(layout = R.layout.chip_item)
abstract class ChipModel : EpoxyModelWithHolder<ChipHolder>() {

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    @EpoxyAttribute
    lateinit var title: CharSequence

    override fun bind(holder: ChipHolder) {
        super.bind(holder)
        holder.binding.apply {
            healthChip.setOnClickListener(itemClickListener)
            scienceChip.setOnClickListener(itemClickListener)
            techChip.setOnClickListener(itemClickListener)
            businessChip.setOnClickListener(itemClickListener)

        }
    }
}

class ChipHolder : EpoxyHolder() {
    lateinit var binding: ChipItemBinding

    override fun bindView(itemView: View) {
        binding = ChipItemBinding.bind(itemView)
    }

}