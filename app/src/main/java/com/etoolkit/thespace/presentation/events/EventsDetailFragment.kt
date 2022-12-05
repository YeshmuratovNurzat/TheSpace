package com.etoolkit.thespace.presentation.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentEventsDetailBinding
import com.etoolkit.domain.events.model.Event

class EventsDetailFragment : Fragment() {

    private var _binding : FragmentEventsDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var event : Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        event = arguments?.getSerializable("event") as Event
        Log.d("MyLog","resultEvent = $event")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentEventsDetailBinding.inflate(layoutInflater,container,false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(event.feature_image)
            .centerCrop()
            .placeholder(R.drawable.ic_newspaper)
            .into(binding.eventImage)

        binding.eventName.text = event.name
        binding.eventDate.text = event.date
        binding.eventDescription.text = event.description
        binding.eventLocation.text = event.location

        binding.toolbarName.text = event.name

        val adapter = EventsProgramAdapter()
        binding.eventDetailRc.adapter = adapter

        adapter.setListData(event.program)

        binding.back.setOnClickListener {
            navController.navigate(R.id.action_eventsDetailFragment_to_eventsFragment)
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