package com.etoolkit.thespace.presentation.astronauts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemAstronautBinding
import com.etoolkit.domain.astronauts.model.Astronaut

class AstronautsAdapter : RecyclerView.Adapter<AstronautsAdapter.AstronautsHolder>() {

    private var astronauts = emptyList<Astronaut>()

    var onClick : ((Astronaut) -> Unit)? = null

    class AstronautsHolder(private val binding: ItemAstronautBinding) : RecyclerView.ViewHolder(binding.root){

        lateinit var cardView : CardView

        fun bind(astronaut: Astronaut){

            Glide.with(binding.root)
                .load(astronaut.profile_image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_account)
                .into(binding.astronautImage)

            binding.astronautName.text = astronaut.name
            binding.astronautAgency.text = astronaut.agency.name
            binding.astronautNationality.text = astronaut.nationality

            cardView = binding.cardContainer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AstronautsHolder {
        val binding = ItemAstronautBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AstronautsHolder(binding)
    }

    override fun onBindViewHolder(holder: AstronautsHolder, position: Int) {

        holder.bind(astronauts[position])

        holder.itemView.setOnClickListener {
            onClick?.invoke(astronauts[position])
        }

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rc_anim_two))

    }

    override fun getItemCount(): Int {
        return astronauts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(astronauts : List<Astronaut>) {
        this.astronauts = astronauts
        notifyDataSetChanged()
    }

}