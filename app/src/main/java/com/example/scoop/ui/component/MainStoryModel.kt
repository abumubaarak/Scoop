package com.example.scoop.ui.component

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.View
 import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.scoop.R
import com.example.scoop.databinding.MainNewsItemBinding

@EpoxyModelClass(layout = R.layout.main_news_item)
abstract class MainStoryModel : EpoxyModelWithHolder<MainStoryHolder>() {


    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    @EpoxyAttribute
    var imageUrl: String? = ""

    @EpoxyAttribute
    var newsTitle: String = ""
    override fun bind(holder: MainStoryHolder) {
        super.bind(holder)
        holder.binding.apply {

            title.text = newsTitle
            blur.alpha = 0.88f
            Glide.with(holder.context)
                .load(imageUrl).into(imageMain)

            imageMain.setOnClickListener(itemClickListener)
        }
    }

}

class MainStoryHolder : EpoxyHolder() {
    lateinit var binding: MainNewsItemBinding
    lateinit var context: Context
    override fun bindView(itemView: View) {
        binding = MainNewsItemBinding.bind(itemView)
        context = itemView.context
    }

}