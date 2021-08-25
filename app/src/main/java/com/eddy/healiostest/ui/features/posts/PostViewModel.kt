package com.eddy.healiostest.ui.features.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.repository.exception.NetworkException
import com.eddy.healiostest.data.repository.util.Resource
import com.eddy.healiostest.domain.dispatchers.AppDispatchers
import com.eddy.healiostest.domain.usecase.PostsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PostViewModel(
    private val appDispatchers: AppDispatchers,
    private val postsUseCase: PostsUseCase
) : ViewModel() {

    // it is good to "emit" data wrapped to custom made Events to the view layer but I just skip it for simplicity
    private val _postMutableLiveData = MutableLiveData<List<PostEntity>>()
    val postLiveData: LiveData<List<PostEntity>> = _postMutableLiveData

    private val _loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingMutableLiveData

    private val _messageMutableLiveData = MutableLiveData<String?>()
    val messageLiveData: LiveData<String?> = _messageMutableLiveData

    fun getPosts() = postsUseCase.getAllPosts().catch {
        Log.d("PostViewModel", it.message ?: "Unknown error")
    }.flowOn(appDispatchers.ioDispatchers)
        .onEach {
            when (it) {
                is Resource.Error -> {
                    _loadingMutableLiveData.postValue(false)
                    // there will be better to create Events and show localized strings from resource, but because of time I skip it
                    when (it.exception) {
                        is NetworkException -> _messageMutableLiveData.postValue(it.exception.message)
                        else -> _messageMutableLiveData.postValue("Something went wrong")
                    }
                    it.data?.let { data ->
                        _postMutableLiveData.postValue(data)
                    }
                }
                is Resource.Loading -> {
                    it.data?.let { data ->
                        _postMutableLiveData.postValue(data)
                    }
                    _loadingMutableLiveData.postValue(true)
                }
                is Resource.Success -> {
                    _loadingMutableLiveData.postValue(false)
                    _postMutableLiveData.postValue(it.data)
                }
            }
        }.flowOn(appDispatchers.mainDispatchers).launchIn(viewModelScope)
}