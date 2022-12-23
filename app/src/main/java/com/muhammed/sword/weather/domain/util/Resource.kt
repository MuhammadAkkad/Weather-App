package com.muhammed.sword.weather.domain.util


data class Resource<out T>(val status: Status, val data: T?, val error: String?) :
    java.io.Serializable {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: String): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
}
