package com.example.tmbdmovies.common

interface Mapper<T, E> {

    //fun from(e: E): T

    fun to(t: T): E
}