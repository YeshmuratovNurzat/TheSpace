package com.etoolkit.thespace.presentation.agencies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemAgenciesBinding
import com.etoolkit.domain.agencies.model.Agencies

class AgenciesAdapter : RecyclerView.Adapter<AgenciesAdapter.AgenciesViewHolder>() {

    private var agencies = emptyList<Agencies>()

    var onClick : ((Agencies) -> Unit)? = null

    class AgenciesViewHolder(private val binding : ItemAgenciesBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(agencies: Agencies){

            Glide.with(binding.root)
                .load(agencies.image_url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_account)
                .into(binding.agenciesImage)

            binding.agenciesName.text = agencies.name
            binding.agenciesStatus.text = agencies.type

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  AgenciesViewHolder {
        val binding = ItemAgenciesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AgenciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgenciesViewHolder, position: Int) {
        holder.bind(agencies[position])

        holder.itemView.setOnClickListener {
            onClick?.invoke(agencies[position])
        }
    }

    override fun getItemCount(): Int {
        return agencies.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(agencies : List<Agencies>) {
        this.agencies = agencies
        notifyDataSetChanged()
    }
}