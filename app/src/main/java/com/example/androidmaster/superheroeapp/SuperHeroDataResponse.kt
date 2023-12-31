package com.example.androidmaster.superheroeapp

import com.google.gson.annotations.SerializedName
//this class returns a list of super heroes

data class SuperHeroDataResponse(@SerializedName("response") val response: String,
                                 @SerializedName("results") val superHeroes:List<SuperHeroItemResponse>) {
}

data class SuperHeroItemResponse(@SerializedName("id") val superHeroId: String,
                                 @SerializedName("name") val name: String,
                                 @SerializedName("image") val superHeroImage: SuperHeroImageResponse)

data class SuperHeroImageResponse(
    @SerializedName("url") val url: String
)