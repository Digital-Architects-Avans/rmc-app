package com.digitalarchitects.rmc_app.data.auth

sealed class AuthResult<T>(val data: T? = null) {
    class Authorized<T>(data: T? = null): AuthResult<T>(data)
    class Unauthorized<T>: AuthResult<T>()
    class UnknownError<T>: AuthResult<T>()
    class NoConnectionError<T>: AuthResult<T>()
}
