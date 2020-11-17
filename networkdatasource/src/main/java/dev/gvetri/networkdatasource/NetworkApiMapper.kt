package dev.gvetri.networkdatasource

import dev.gvetri.apimodel.ApiDummyClass
import dev.gvetri.apimodel.DogApi
import dev.gvetri.model.Dog
import dev.gvetri.model.MyDummyClass


fun apiDogToDogMapper(dogApi: DogApi?): Dog {
    return Dog(dogApi?.url)
}

fun apiDummyToDummy(apiDummyClass: ApiDummyClass?): MyDummyClass {
    return MyDummyClass(apiDummyClass?.id)
}