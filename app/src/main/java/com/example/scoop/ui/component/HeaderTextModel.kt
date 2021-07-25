package com.example.scoop.ui.component

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.scoop.R
import com.example.scoop.databinding.HeaderTitleBinding

@EpoxyModelClass(layout = R.layout.header_title)
abstract class HeaderTextModel: EpoxyModelWithHolder<HeaderHolder>() {

    @field:EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    open var itemClickListener: View.OnClickListener? = null

    @EpoxyAttribute lateinit var title:CharSequence
    override fun bind(holder: HeaderHolder) {
        super.bind(holder)
        holder.binding.apply {
            headerTitle.text=title
            toggleMode.setOnClickListener(itemClickListener)
        }
    }
}

class HeaderHolder: EpoxyHolder() {
    lateinit var binding: HeaderTitleBinding
    override fun bindView(itemView: View) {
        binding= HeaderTitleBinding.bind(itemView)
    }

}