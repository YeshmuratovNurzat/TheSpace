package com.etoolkit.thespace.presentation.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etoolkit.thespace.databinding.ItemEventDetailLauchesBinding
import com.etoolkit.domain.events.model.Launche

class EventsLaunchesAdapter : RecyclerView.Adapter<EventsLaunchesAdapter.EventsLaunchesViewHolder>() {

    private var launches = emptyList<Launche>()

    class EventsLaunchesViewHolder(val binding: ItemEventDetailLauchesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(launche: Launche){
            binding.programTitle.text = launche.name
            binding.programDescription.text = launche.infographic
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsLaunchesViewHolder {
        val binding = ItemEventDetailLauchesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EventsLaunchesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsLaunchesViewHolder, position: Int) {
        holder.bind(launches[position])
    }

    override fun getItemCount(): Int {
        return launches.size
    }
}