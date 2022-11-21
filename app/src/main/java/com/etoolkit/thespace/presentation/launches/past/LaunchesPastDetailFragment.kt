package com.etoolkit.thespace.presentation.launches.past

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentLaunchesDetailPastBinding
import com.etoolkit.domain.launches.model.Launch

class LaunchesPastDetailFragment : Fragment() {

    private var _binding : FragmentLaunchesDetailPastBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var launch : Launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch = arguments?.getSerializable("launchPast") as Launch
        Log.d("MyLog","resultLaunchPast = $launch")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchesDetailPastBinding.inflate(layoutInflater,container,false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(launch.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_rocket)
            .into(binding.launchDetailImage)

        binding.launchName.text = launch.name ?: " "
        binding.launchStatus.text = launch.status.name
        binding.launchInfographic.text = launch.infographic
        binding.launchDescription.text = launch.status.description
        binding.launchLocation.text = launch.pad.location.name
        binding.launchMission.text = launch.mission.description
        binding.launchServiceProvider.text = launch.launch_service_provider.name

        binding.toolbarName.text = launch.name

        if(launch.infographic != null){
            binding.launchInfographic.visibility = View.VISIBLE
            binding.launchInfographic.text = launch.infographic
        }

        if (launch.status.name == "Launch Failure") {
            binding.launchStatus.setTextColor(Color.parseColor("#FF0000")) //red
        } else {
            binding.launchStatus.setTextColor(Color.parseColor("#008000")) //green
        }

        binding.back.setOnClickListener {
            navController.navigate(R.id.action_launchesPastDetailFragment_to_launchesMainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = LaunchesPastDetailFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}