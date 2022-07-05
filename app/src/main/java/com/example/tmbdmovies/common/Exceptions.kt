package com.example.tmbdmovies.common

class NoInternetException(message: String? = "Check you internet connection") :
    RuntimeException(message)

/**
 * General network API exception.
 */
open class ApiException(val code: Int, message: String? = "Code: $code") : RuntimeException(message)

/**
 * Network API exception thrown when response is 500 (server error).
 */
class InternalServerErrorException(
    code: Int = 500,
    message: String? = "Internal server error (code: $code)"
) :
    ApiException(code, message)

class ServerMessageApiException(
    code: Int,
    message: String?
) : ApiException(code, message)

/**
 * Network API exception thrown when response is 204 (no content).
 */
class NoContentSuccessException(
    code: Int = 204,
    message: String? = "No content (code: $code)"
) : ApiException(code, message)

/**
 * Network API exception thrown when response is success but neither 200, 201 nor 204 .
 */
class CustomSuccessException(
    code: Int,
    message: String? = "Success response exception (code: $code)"
) : ApiException(code, message)
