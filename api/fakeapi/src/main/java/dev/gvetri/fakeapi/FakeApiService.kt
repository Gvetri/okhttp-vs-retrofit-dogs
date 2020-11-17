package dev.gvetri.fakeapi

import dev.gvetri.api.ApiService
import dev.gvetri.apimodel.ApiDummyClass
import retrofit2.Response

class FakeApiService : ApiService {

    override fun retrieveSomeData(): Response<ApiDummyClass> {
        TODO("Not yet implemented")
    }
}