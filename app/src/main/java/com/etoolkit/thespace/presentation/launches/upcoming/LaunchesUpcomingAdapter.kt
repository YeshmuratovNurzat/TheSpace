package com.etoolkit.thespace.presentation.launches.upcoming

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ItemLaunchUpcomingBinding
import com.etoolkit.domain.launches.model.Launch
import java.text.SimpleDateFormat
import java.util.*

class LaunchesUpcomingAdapter : RecyclerView.Adapter<LaunchesUpcomingAdapter.LaunchesUpcomingViewHolder>() {

    private var launches = emptyList<Launch>()

    var onClick : ((Launch) -> Unit)? = null

    class LaunchesUpcomingViewHolder(private val binding: ItemLaunchUpcomingBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(launch: Launch){

            val countDownTimer: CountDownTimer

            Glide.with(binding.root)
                .load(launch.image)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_rocket)
                .into(binding.eventImage)

            binding.eventName.text = launch.name

            val currentTime = Calendar.getInstance().time
            val endDateDay = launch.window_start
            val format1 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val endDate = format1.parse(endDateDay)

            //milliseconds
            val different = endDate!!.time - currentTime.time
            countDownTimer = object : CountDownTimer(different, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    var diff = millisUntilFinished
                    val secondsInMilli: Long = 1000
                    val minutesInMilli = secondsInMilli * 60
                    val hoursInMilli = minutesInMilli * 60
                    val daysInMilli = hoursInMilli * 24

                    val elapsedDays = diff / daysInMilli
                    diff %= daysInMilli

                    val elapsedHours = diff / hoursInMilli
                    diff %= hoursInMilli

                    val elapsedMinutes = diff / minutesInMilli
                    diff %= minutesInMilli

                    val elapsedSeconds = diff / secondsInMilli

                    binding.eventData.text = "$elapsedDays days $elapsedHours hs $elapsedMinutes min $elapsedSeconds sec"
                }

                override fun onFinish() {
                    binding.eventStatus.text = "done!"
                }
            }.start()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesUpcomingViewHolder {
        val binding = ItemLaunchUpcomingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LaunchesUpcomingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaunchesUpcomingViewHolder, position: Int) {
        holder.bind(launches[position])

        holder.itemView.setOnClickListener {
            onClick?.invoke(launches[position])
        }
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