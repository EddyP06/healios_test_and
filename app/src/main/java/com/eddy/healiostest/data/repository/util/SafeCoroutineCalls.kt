package com.eddy.healiostest.data.repository.util

import com.eddy.healiostest.data.repository.exception.NetworkException
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.net.UnknownHostException

inline fun <RESULT, RESPONSE> safeCachedApiCall(
    crossinline databaseSource: () -> Flow<RESULT>,
    crossinline apiCall: suspend () -> Response<RESPONSE>,
    crossinline databaseEmitter: suspend (RESPONSE) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = {},
    crossinline shouldFetchFromApi: (RESULT) -> Boolean = { true }
): Flow<Resource<RESULT>> = flow {
    emit(Resource.Loading(null))
    val preCachedData = databaseSource.invoke().catch { }.first()

    val cachedFlow = if (shouldFetchFromApi.invoke(preCachedData)) {
        emit(Resource.Loading(preCachedData))
        try {
            val responseResult = apiCall.invoke()
            if (responseResult.isSuccessful) {
                responseResult.body()?.let {
                    databaseEmitter.invoke(it)
                }
            }
            databaseSource.invoke().map { Resource.Success(it) }
        } catch (t: Throwable) {
            val exception = when (t) {
                is UnknownHostException -> {
                    NetworkException()
                }
                else -> {
                    Exception("Could not fetch from network")
                }
            }
            onFetchFailed.invoke(t)
            databaseSource.invoke().map {
                Resource.Error(exception, it)
            }.catch {
                onFetchFailed.invoke(it)
                Resource.Error(Exception("Unknown error"), preCachedData)
            }
        }
    } else {
        databaseSource.invoke().map { Resource.Success(it) }
    }
    emitAll(cachedFlow)
}