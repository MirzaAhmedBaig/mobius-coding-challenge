package com.mobius.coding_challenge.data.repositories

import com.mobius.coding_challenge.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {
    private val TAG = "BaseRepository"
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                throwable.printStackTrace()

                when (throwable) {
                    is HttpException -> {
                        Resource.Failure(
                            false,
                            throwable.code(),
                            null,
                            throwable.response()?.errorBody(),
                            throwable
                        )
                    }
                    else -> Resource.Failure(true, null, throwable.message, null, throwable)
                }
            }
        }
    }
}