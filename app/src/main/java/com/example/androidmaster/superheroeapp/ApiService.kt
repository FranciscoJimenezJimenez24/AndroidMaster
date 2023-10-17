package com.example.androidmaster.superheroeapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    //this method find by name of the super hero
    @GET("/api/10229233666327556/search/{name}")
    suspend fun getSuperHeroes(@Path("name") superHeroName:String):Response<SuperHeroDataResponse>

    @GET("/api/10229233666327556/{id}")
    suspend fun getSuperHeroInfo(@Path("id") superHeroId: String):Response<SuperHeroDetailResponse>
}