package com.etoolkit.thespace.presentation.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemEventBinding
import com.etoolkit.domain.events.model.Event

class EventsAdapter : RecyclerView.Adapter<EventsAdapter.EventsHolder>(){

    var onClick : ((Event) -> Unit)? = null

    private var events = emptyList<Event>()

    class EventsHolder(private val binding: ItemEventBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(event : Event){

            Glide.with(binding.root)
                .load(event.feature_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_newspaper)
                .into(binding.eventImage)

            binding.eventName.text = event.name
            binding.eventData.text = event.date
            binding.eventStatus.text = event.type.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EventsHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsHolder, position: Int) {
        holder.bind(events[position])

        holder.itemView.setOnClickListener {
            onClick?.invoke(events[position])
        }
    }

    override fun getItemCount(): Int {
        return events.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(events : List<Event>) {
        this.events = events
        notifyDataSetChanged()
    }
}