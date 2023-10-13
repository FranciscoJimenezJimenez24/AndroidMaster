package com.example.androidmaster.superheroeapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding

class SuperHeroViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse){
        binding.tvSuperHeroName.text=superHeroItemResponse.name
    }
}