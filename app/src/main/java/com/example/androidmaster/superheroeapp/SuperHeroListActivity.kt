package com.example.androidmaster.superheroeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter:SuperHeroAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit=getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.svSearchEngine.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?)=false

        })

        adapter = SuperHeroAdapter()
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager=LinearLayoutManager(this)
        binding.rvSuperHero.adapter=adapter
    }

    private fun searchByName(query:String){
        binding.progressBar.isVisible=true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<SuperHeroDataResponse> =retrofit.create(ApiService::class.java).getSuperHeroes(query)
            if (myResponse.isSuccessful){
                val response:SuperHeroDataResponse?=myResponse.body()
                if (response!=null){
                    Log.i("paco",response.toString())
                    //principal thread
                    runOnUiThread {
                        adapter.updateList(response.superHeroes)
                        binding.progressBar.isVisible=false
                    }
                }
                Log.i("paco","works")
            }else{
                Log.i("paco","Doesn't works")
            }
        }
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}