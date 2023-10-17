package com.example.androidmaster.superheroeapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)
    //this method pass the super hero to the adapter
    //root is all the view
    fun bind(superHeroItemResponse: SuperHeroItemResponse,onItemSelected:(String) ->Unit){
        binding.tvSuperHeroName.text=superHeroItemResponse.name
        Picasso.get().load(superHeroItemResponse.superHeroImage.url).into(binding.ivSuperHero)
        binding.root.setOnClickListener{onItemSelected(superHeroItemResponse.superHeroId)}
    }
}