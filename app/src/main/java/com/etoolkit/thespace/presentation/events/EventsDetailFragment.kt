package com.etoolkit.thespace.presentation.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentEventsDetailBinding
import com.etoolkit.thespace.domain.events.model.Event

class EventsDetailFragment : Fragment() {

    private var _binding : FragmentEventsDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var event : Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = arguments?.getSerializable("event") as Event
        Log.d("MyLog","resultEvent = $event")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEventsDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(event.feature_image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.eventImage)

        binding.eventName.text = event.name
        binding.eventDate.text = event.date
        binding.eventDescription.text = event.description
        binding.eventLocation.text = event.location

        val adapter = EventsProgramAdapter()
        binding.eventDetailRc.adapter = adapter

        adapter.setListData(event.program)

        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

    }

    companion object {

        fun newInstance() = EventsDetailFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}