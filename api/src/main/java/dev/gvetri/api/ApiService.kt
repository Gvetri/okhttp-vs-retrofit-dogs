package dev.gvetri.api

import retrofit2.Response

interface ApiService {
    fun retrieveSomeData() : Response<Any>
}