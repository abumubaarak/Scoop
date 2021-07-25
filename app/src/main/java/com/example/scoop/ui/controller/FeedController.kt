package com.example.scoop.ui.controller

import android.app.Activity
import com.airbnb.epoxy.*
import com.example.scoop.R
import com.example.scoop.domain.News
import com.example.scoop.ui.adapter.component.*
import com.example.scoop.ui.component.*
import com.example.scoop.utils.ThemeHelper
import com.thefinestartist.finestwebview.FinestWebView
import timber.log.Timber


class FeedController(private val listener: ItemClickLister,private val activity:Activity) : EpoxyController(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    interface ItemClickLister {
        fun item(category: String)
    }

    var activeTab: Int = R.id.health_chip
    var isDark:Boolean=false

    var topHeadline: List<News> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var everything: List<News> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        headerText {
            id("header")
            title("Today's Read")
            itemClickListener { model, parentView, clickedView, position ->
                if (this@FeedController.isDark){
                    ThemeHelper.applyTheme("light")
                 }else{
                    ThemeHelper.applyTheme("dark")
                    this@FeedController.isDark=true

                }
             }

        }

        val model = everything.map { _it ->
            MainStoryModel_()
                .id(_it.title)
                .newsTitle(_it.title)
                .imageUrl(_it.urlToImage)
                .itemClickListener { model, parentView, clickedView, position ->
                _it.url?.let { FinestWebView.Builder(this@FeedController.activity)
                    .showSwipeRefreshLayout(false)
                    .show(it) }
            }
        }

        carousel {
            id("Main")
            numViewsToShowOnScreen(1.14f)
            hasFixedSize(true)
            padding(Carousel.Padding(0, 0, 0, 0, 25))
            models(model)

        }

        subHeaderText {
            id("sub_header")
            title("For you")
        }


        chip {
            id("chips")
            itemClickListener { model, parentView, clickedView, position ->
                this@FeedController.activeTab = clickedView.id
                when (clickedView.id) {
                    R.id.health_chip -> {
                        this@FeedController.setCategory("Health", R.id.health_chip)
                    }
                    R.id.science_chip -> {
                        this@FeedController.setCategory("Science", R.id.science_chip)
                        Timber.i(R.id.science_chip.toString())
                    }
                    R.id.tech_chip -> {
                        this@FeedController.setCategory("Technology", R.id.tech_chip)

                    }
                    R.id.business_chip -> {
                        this@FeedController.setCategory("Business", R.id.business_chip)
                    }
                }
            }
        }

        topHeadline.forEach { _it ->
            if (_it.sourceId != null) {
                fullCard {
                    id(_it.title.subSequence(0, 3))
                    title(_it.title)
                    _it.urlToImage?.let { imageUrl(it) }
                    itemClickListener { model, parentView, clickedView, position ->
                        _it.url?.let { FinestWebView.Builder(this@FeedController.activity)
                            .showSwipeRefreshLayout(false)

                            .show(it) }
                    }
                }
            } else {

                shortCard {
                    id(_it.title.subSequence(0, 5))
                    newsTitle(_it.title)
                    _it.urlToImage?.let { imageUrl(it) }
                    itemClickListener { model, parentView, clickedView, position ->
                        _it.url?.let { FinestWebView.Builder(this@FeedController.activity).show(it) }
                    }
                }
            }


        }


    }

    private fun setCategory(item: String, id: Int) {
        this@FeedController.listener.item(item)
    }
}