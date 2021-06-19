package com.schaefer.home.data.api

import com.schaefer.home.data.model.BreedResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

internal interface TheCatAPI {
    @GET("v1/breeds")
    fun getBreeds(): Single<BreedResponse>
}