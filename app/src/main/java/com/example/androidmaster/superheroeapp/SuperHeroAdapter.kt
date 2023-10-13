package com.example.androidmaster.superheroeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class SuperHeroAdapter(private var superHeroList: List<SuperHeroItemResponse> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superHeroList: List<SuperHeroItemResponse>){
        this.superHeroList=superHeroList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: SuperHeroViewHolder, position: Int) {
        viewHolder.bind(superHeroList[position])
    }

    override fun getItemCount() = superHeroList.size
}