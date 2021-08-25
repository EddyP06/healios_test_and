package com.eddy.healiostest.ui.features.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddy.healiostest.data.models.entity.CommentEntity
import com.eddy.healiostest.data.models.entity.PostEntity
import com.eddy.healiostest.data.models.entity.UserEntity
import com.eddy.healiostest.data.repository.exception.NetworkException
import com.eddy.healiostest.data.repository.util.Resource
import com.eddy.healiostest.domain.dispatchers.AppDispatchers
import com.eddy.healiostest.domain.usecase.DetailsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DetailsViewModel(
    private val appDispatchers: AppDispatchers,
    private val detailsUseCase: DetailsUseCase
) : ViewModel() {

    private val _commentMutableLiveData = MutableLiveData<List<CommentEntity>>()
    val commentLiveData: LiveData<List<CommentEntity>> = _commentMutableLiveData

    private val _userMutableLiveData = MutableLiveData<UserEntity>()
    val userLiveData: LiveData<UserEntity> = _userMutableLiveData

    private val _loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingMutableLiveData

    private val _messageMutableLiveData = MutableLiveData<String?>()
    val messageLiveData: LiveData<String?> = _messageMutableLiveData

    fun getComments(postId: Long) = detailsUseCase.getComments(postId).catch {
        Log.d("DetailViewModel", it.message ?: "Unknown error")
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
                        _commentMutableLiveData.postValue(data)
                    }
                }
                is Resource.Loading -> {
                    it.data?.let { data ->
                        _commentMutableLiveData.postValue(data)
                    }
                    _loadingMutableLiveData.postValue(true)
                }
                is Resource.Success -> {
                    _loadingMutableLiveData.postValue(false)
                    _commentMutableLiveData.postValue(it.data)
                }
            }
        }.flowOn(appDispatchers.mainDispatchers).launchIn(viewModelScope)

    fun getUser(userId: Long) = detailsUseCase.getUserData(userId).catch {
        Log.d("DetailViewModel", it.message ?: "Unknown error")
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
                        _userMutableLiveData.postValue(data)
                    }
                }
                is Resource.Loading -> {
                    it.data?.let { data ->
                        _userMutableLiveData.postValue(data)
                    }
                    _loadingMutableLiveData.postValue(true)
                }
                is Resource.Success -> {
                    _loadingMutableLiveData.postValue(false)
                    _userMutableLiveData.postValue(it.data)
                }
            }
        }.flowOn(appDispatchers.mainDispatchers).launchIn(viewModelScope)
}