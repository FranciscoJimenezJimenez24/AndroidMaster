package com.example.androidmaster.superheroeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailSuperHeroBinding
    companion object{
        const val EXTRA_ID="extra_id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id=intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperHeroInformation(id)
    }
    //this method request to internet the information of one super heroes that exists in the api
    private fun getSuperHeroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superHeroDetail=getRetrofit().create(ApiService::class.java).getSuperHeroInfo(id)

            if (superHeroDetail.body()!=null){
                runOnUiThread { createUI(superHeroDetail.body()!!) }

            }
        }
    }

    private fun createUI(superHero: SuperHeroDetailResponse) {

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}