package com.example.scoop.ui.component

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.scoop.R
import com.example.scoop.databinding.HeaderTitleBinding
import com.example.scoop.databinding.SubHeaderTitleBinding

@EpoxyModelClass(layout = R.layout.sub_header_title)
abstract class SubHeaderTextModel: EpoxyModelWithHolder<SubHeaderHolder>() {

    @EpoxyAttribute lateinit var title:CharSequence
    override fun bind(holder: SubHeaderHolder) {
        super.bind(holder)
        holder.binding.apply {
            headerTitle.text=title
        }
    }
}

class SubHeaderHolder: EpoxyHolder() {
    lateinit var binding: SubHeaderTitleBinding
    override fun bindView(itemView: View) {
        binding= SubHeaderTitleBinding.bind(itemView)
    }

}