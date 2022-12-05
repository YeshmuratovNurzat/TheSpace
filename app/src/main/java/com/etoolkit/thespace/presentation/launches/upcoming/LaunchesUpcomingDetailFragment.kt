package com.etoolkit.thespace.presentation.launches.upcoming

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentLaunchesUpcomingDetailBinding
import com.etoolkit.domain.launches.model.Launch
import com.google.android.material.transition.MaterialContainerTransform

class LaunchesUpcomingDetailFragment : Fragment() {

    private var _binding : FragmentLaunchesUpcomingDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController
    private lateinit var launch : Launch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch = arguments?.getSerializable("launchUpcoming") as Launch
        Log.d("MyLog","resultLaunchUpcoming = $launch")

        val animation = androidx.transition.TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchesUpcomingDetailBinding.inflate(layoutInflater)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(binding.root)
            .load(launch.image)
            .centerCrop()
            .placeholder(R.drawable.ic_launches)
            .into(binding.launchUpcomingImage)

        binding.launchUpcomingName.text = launch.name
        binding.launchUpcomingInfographic.text = launch.failreason

        binding.back.setOnClickListener {
            navController.navigate(R.id.action_launchesUpcomingDetailFragment_to_launchesMainFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = LaunchesUpcomingDetailFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}