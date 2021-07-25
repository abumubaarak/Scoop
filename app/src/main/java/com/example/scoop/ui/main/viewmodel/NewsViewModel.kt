package com.example.scoop.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scoop.data.local.model.LocalNews
import com.example.scoop.repository.MainRepository
import com.example.scoop.data.remote.model.NewsResponse
import com.example.scoop.domain.News
import com.example.scoop.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@HiltViewModel

class NewsViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {


    private val _topHeadline = MutableLiveData<Result<List<News>>>()
    var topHeadline: LiveData<Result<List<News>>> = _topHeadline

    private val _everything = MutableLiveData<Result<List<News>>>()
    var everything: LiveData<Result<List<News>>> = _everything


    init {
        getEverything()
        getTopHeadline()
    }

    fun getTopHeadline(category: String = "Health") {
        viewModelScope.launch {
            mainRepository.getTopHeadline(
                category
            ).collect {
                _topHeadline.value = it
                topHeadline = _topHeadline
            }
        }
    }

    private fun getEverything() {
        viewModelScope.launch {
            mainRepository.getEverything().collect {
                _everything.value = it
                everything = _everything
            }
        }
    }

}