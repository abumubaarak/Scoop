package com.example.scoop.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.scoop.R

import com.example.scoop.databinding.ActivityMainBinding
import com.example.scoop.ui.controller.FeedController
import com.example.scoop.ui.main.viewmodel.NewsViewModel
import com.thefinestartist.finestwebview.FinestWebView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
@ExperimentalTime

class MainActivity : AppCompatActivity() {


    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = object : FeedController.ItemClickLister {
            override fun item(category: String) {
                newsViewModel.getTopHeadline(category)
            }
        }

         val controller = FeedController(listener,this)

        binding.recycler.setControllerAndBuildModels(controller)


        newsViewModel.everything.observe(this, Observer {

            with(it) {
                if (isSuccess()) {
                    if (data != null) {
                        Timber.i(data.size.toString())
                        binding.progress.visibility = GONE
                        controller.everything = data
                    }
                } else if (isLoading()) {
                    binding.progress.isIndeterminate = true
                    binding.progress.visibility = VISIBLE
                } else if (isError()) {
                    binding.progress.visibility = GONE

                }


            }
        })
        newsViewModel.topHeadline.observe(this, Observer {
            with(it) {
                if (isSuccess()) {
                    if (data != null) {
                        Timber.i(data.size.toString())
                        binding.progress.visibility = GONE
                        controller.topHeadline = data
                    }
                } else if (isLoading()) {
                    binding.progress.isIndeterminate = true
                    binding.progress.visibility = VISIBLE
                } else if (isError()) {
                    binding.progress.visibility = GONE

                }


            }

        })
    }

}