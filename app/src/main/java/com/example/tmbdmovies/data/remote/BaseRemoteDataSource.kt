package com.example.tmbdmovies.data.remote

import com.example.tmbdmovies.common.*
import com.example.tmbdmovies.data.models.BaseResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

open class BaseRemoteDataSource {

    @Throws(
        ApiException::class,
        InternalServerErrorException::class,
        ServerMessageApiException::class
    )
    protected suspend fun <T: Any> makeRequest(call: suspend ()-> Response<T>): T {
        val result = call()
        return when(result.isSuccessful) {
            false -> {
                val responseCode = result.code()
                val errorResponse = getErrorResponse(result.errorBody())
                when{
                    responseCode == 500 -> throw InternalServerErrorException()
                    errorResponse?.status_message != null -> throw ServerMessageApiException(
                        responseCode,
                        errorResponse.status_message
                    )
                    else -> throw ApiException(
                        responseCode,
                        "An error occurred, please try again"
                    )
                }
            }
            true -> {
                when (result.code()) {
                    200, 201 -> result.body()!!
                    204 -> throw NoContentSuccessException()
                    else -> throw CustomSuccessException(code = result.code())
                }
            }
        }
    }

    private fun getErrorResponse(errorBody: ResponseBody?): BaseResponse? {
        return try {
            Gson().fromJson(
                convertResponseBodyToString(errorBody),
                BaseResponse::class.java
            )
        } catch (e: Exception) {
            null
        }
    }

    private fun convertResponseBodyToString(responseBody: ResponseBody?): String {
        try {
            return responseBody!!.string()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}