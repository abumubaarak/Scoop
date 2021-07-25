package com.example.scoop.ui.component

import android.content.Context
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.scoop.R
import com.example.scoop.databinding.FullCardItemBinding

@EpoxyModelClass(layout = R.layout.full_card_item)
abstract class FullCardModel : EpoxyModelWithHolder<FullCardHolder>() {


    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    @EpoxyAttribute
    lateinit var title: CharSequence
    @EpoxyAttribute
     var imageUrl: String=""

    override fun bind(holder: FullCardHolder) {
        super.bind(holder)
        holder.binding.apply {
            newsTitle.text = title
            Glide.with(holder.context)
                .load(imageUrl).
                into(newsImage)
            fullCard.setOnClickListener(itemClickListener)
        }
    }


}

class FullCardHolder : EpoxyHolder() {
    lateinit var binding: FullCardItemBinding
    lateinit var context: Context
    override fun bindView(itemView: View) {
        binding = FullCardItemBinding.bind(itemView)
        context = itemView.context
    }

}