package com.etoolkit.thespace.presentation.launches

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemLaunchBinding
import com.etoolkit.thespace.domain.launches.model.Launch

class LaunchesAdapter : RecyclerView.Adapter<LaunchesAdapter.LaunchesHolder>() {

    private var launches = emptyList<Launch>()

    var onClick : ((Launch) -> Unit)? = null

    class LaunchesHolder(private val binding: ItemLaunchBinding) : RecyclerView.ViewHolder(binding.root){

        lateinit var cardView: CardView

        fun bind(launches: Launch){

            Glide.with(binding.root)
                .load(launches.image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_rocket)
                .into(binding.launchImage)

            binding.launchName.text = launches.name
            binding.launchMission.text = launches.mission.name
            binding.launchStatus.text = launches.status.name

            cardView = binding.cardContainer

            if(launches.status.name == "Launch Failure"){
                binding.launchStatus.setTextColor(Color.parseColor("#FF0000")) //red
            }else{
                binding.launchStatus.setTextColor(Color.parseColor("#008000")) //green
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesHolder {
        val binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaunchesHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchesHolder, position: Int) {

        holder.bind(launches[position])

        holder.itemView.setOnClickListener {
            onClick?.invoke(launches[position])
        }

        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,R.anim.rc_anim_two))
    }

    override fun getItemCount(): Int {
        return launches.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(launches : List<Launch>) {
        this.launches = launches
        notifyDataSetChanged()
    }
}