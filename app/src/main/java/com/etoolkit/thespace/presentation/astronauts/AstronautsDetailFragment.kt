package com.etoolkit.thespace.presentation.astronauts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentAstronautsDetailBinding
import com.etoolkit.thespace.domain.astronauts.model.Astronaut

class AstronautsDetailFragment : Fragment() {

    private var _binding: FragmentAstronautsDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var astronaut : Astronaut

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        astronaut = arguments?.getSerializable("astronaut") as Astronaut
        Log.d("MyLog","resultAstronaut = $astronaut")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAstronautsDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(astronaut.profile_image)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.astronautDetailImage)

        binding.astronautName.text = astronaut.name
        binding.astronautNationality.text = astronaut.nationality
        binding.astronautDateOfBirth.text = astronaut.date_of_birth
        binding.astronautStatus.text = astronaut.status.name
        binding.astronautBio.text = astronaut.bio
        binding.astronautAgency.text = astronaut.agency.name
        binding.astronautFirstFlight.text = astronaut.first_flight
        binding.astronautFlightsCount.text = astronaut.flights_count.toString()
        binding.astronautLandingsCount.text = astronaut.landings_count.toString()
        binding.astronautLastFlight.text = astronaut.last_flight


        binding.back.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {

        fun newInstance() = AstronautsDetailFragment().apply {
            arguments = Bundle().apply {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}