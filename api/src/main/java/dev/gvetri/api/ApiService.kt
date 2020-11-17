package dev.gvetri.api

import dev.gvetri.apimodel.ApiDummyClass
import retrofit2.Response

interface ApiService {
    fun retrieveSomeData() : Response<ApiDummyClass>
}