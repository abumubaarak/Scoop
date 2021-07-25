package com.example.scoop.ui.component

import android.content.Context
import android.view.View
import com.airbnb.epoxy.*
import com.bumptech.glide.Glide
import com.example.scoop.R
 import com.example.scoop.databinding.ShortCardItemBinding

@EpoxyModelClass(layout = R.layout.short_card_item)
abstract class ShortCardModel : EpoxyModelWithHolder<ShortCardHolder>() {

    @EpoxyAttribute lateinit var newsTitle: String
    @EpoxyAttribute
    var imageUrl: String=""

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    override fun bind(holder: ShortCardHolder) {
        super.bind(holder)
        holder.binding.apply {
            title.text = newsTitle

            Glide.with(holder.context)
                .load(imageUrl).
                into(newsImage)

            shortCard.setOnClickListener(itemClickListener)
        }
    }
}

class ShortCardHolder : EpoxyHolder() {
    lateinit var binding: ShortCardItemBinding
    lateinit var context: Context

    override fun bindView(itemView: View) {
        binding = ShortCardItemBinding.bind(itemView)
        context = itemView.context

    }

}

