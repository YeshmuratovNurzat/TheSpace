package com.etoolkit.thespace.presentation.agencies

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
import com.etoolkit.thespace.databinding.FragmentAgenciesDetailBinding
import com.etoolkit.domain.agencies.model.Agencies

class AgenciesDetailFragment : Fragment() {

    private var _binding : FragmentAgenciesDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var agencies : Agencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        agencies = arguments?.getSerializable("agencies") as Agencies
        Log.d("MyLog","resultAgencies = $agencies")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAgenciesDetailBinding.inflate(layoutInflater,container,false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(agencies.image_url)
            .centerCrop()
            .placeholder(R.drawable.ic_observatory)
            .into(binding.agenciesImage)

        binding.agenciesName.text = agencies.name
        binding.agenciesAdministrator.text = agencies.administrator
        binding.agenciesCountryCode.text = agencies.country_code
        binding.agenciesDescription.text = agencies.description
        binding.agenciesLaunchers.text = agencies.launchers

        // back
        binding.back.setOnClickListener {
            navController.navigate(R.id.action_agenciesDetailFragment_to_agenciesFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance() = AgenciesDetailFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}