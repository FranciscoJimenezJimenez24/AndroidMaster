package com.example.androidmaster.superheroeapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

//a function lambda, you can use that like a parameter
class SuperHeroAdapter(private var superHeroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected:(String) ->Unit) :
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
        viewHolder.bind(superHeroList[position],onItemSelected)
    }

    override fun getItemCount() = superHeroList.size
}