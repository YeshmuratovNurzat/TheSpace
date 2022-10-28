package com.etoolkit.thespace.presentation.events

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemEventDetailProgramBinding
import com.etoolkit.thespace.domain.events.model.ProgramX

class EventsProgramAdapter : RecyclerView.Adapter<EventsProgramAdapter.EventsProgramViewHolder>() {

    private var programX = emptyList<ProgramX>()

    class EventsProgramViewHolder(val binding: ItemEventDetailProgramBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(programX: ProgramX){

            Glide.with(binding.root)
                .load(programX.image_url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.rocket)
                .into(binding.launchImage)

            binding.programTitle.text = programX.name
            binding.programDescription.text = programX.description

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsProgramViewHolder {
        val binding = ItemEventDetailProgramBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return EventsProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventsProgramViewHolder, position: Int) {
        holder.bind(programX[position])
    }

    override fun getItemCount(): Int {
        return programX.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(programX : List<ProgramX>) {
        this.programX = programX
        notifyDataSetChanged()
    }
}