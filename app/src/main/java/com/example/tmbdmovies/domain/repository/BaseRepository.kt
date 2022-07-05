package com.example.tmbdmovies.domain.repository

import com.example.tmbdmovies.common.NetworkConnectivityHelper
import com.example.tmbdmovies.common.NoInternetException
import com.example.tmbdmovies.common.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeoutException

open class BaseRepository(
    private val networkConnectivityHelper: NetworkConnectivityHelper,
    val dispatcher: CoroutineDispatcher
) {
    fun <T> networkFlowOnly(remoteCall: suspend () -> T): Flow<Resource<T>> {
        suspend fun fetchFromNetwork(): T {
            if (networkConnectivityHelper.isConnected()) {
                return remoteCall()
            } else {
                throw NoInternetException()
            }
        }

        return flow<Resource<T>> {
            emit(Resource.Loading)

            try {
                emit(Resource.Success(fetchFromNetwork()))
            } catch (e: Exception) {
                handleApiException(e, this)
            }
        }.flowOn(dispatcher)
    }

    private suspend fun <T> handleApiException(
        e: Exception,
        flowCollector: FlowCollector<Resource<T>>
    ) {
        e.printStackTrace()
        if (e is TimeoutException)
            flowCollector.emit(Resource.Error(Exception("Failed to load data from server")))
        else
            flowCollector.emit(Resource.Error(e))
    }

    protected fun <T> networkWithCacheFlow(
        remoteCall: suspend () -> T,
        localCall: suspend () -> T?,
        saveLocal: suspend (T) -> Unit
    ): Flow<Resource<T>> {
        suspend fun fetchFromNetwork(): T {
            return remoteCall().also { response ->
                saveLocal(response)
            }
        }

        return flow<Resource<T>> {
            emit(Resource.Loading)

            try {
                val loadDataFromDB = localCall()
                val isLocalDataAvailable = (loadDataFromDB != null)
                if (isLocalDataAvailable) emit(Resource.Success(loadDataFromDB!!))

                if (networkConnectivityHelper.isConnected()) {
                    emit(Resource.Success(fetchFromNetwork()))
                } else {
                    if (!isLocalDataAvailable) emit(
                        Resource.Error(NoInternetException())
                    )
                }

            } catch (e: Exception) {
                handleApiException(e, this)
            }
        }.flowOn(dispatcher)
    }


    inline fun <reified T> localOnlyFlow(crossinline localCall: suspend () -> T): Flow<Resource<T>> {
        return flow {
            emit(Resource.Loading)
            try {
                val data = localCall()
                emit(Resource.Success(data))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e))
            }
        }.flowOn(dispatcher)
    }
}