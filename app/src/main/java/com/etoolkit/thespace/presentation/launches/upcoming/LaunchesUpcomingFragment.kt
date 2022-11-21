package com.etoolkit.thespace.presentation.launches.upcoming

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentLaunchesUpcomingBinding
import com.etoolkit.thespace.presentation.launches.LaunchesViewModel

class LaunchesUpcomingFragment : Fragment() {

    private var _binding : FragmentLaunchesUpcomingBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController
    private val viewModel by viewModels<LaunchesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchesUpcomingBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LaunchesUpcomingAdapter()
        binding.rcView.adapter = adapter

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)

        adapter.onClick = {
            val bundle = Bundle()
            bundle.putSerializable("launchUpcoming",it)
            navController.navigate(R.id.action_launchesMainFragment_to_launchesUpcomingDetailFragment, bundle)
        }

        viewModel.getLaunchesUpcoming()

        viewModel.getLaunchesUpcomingResult.observe(viewLifecycleOwner){
            invisibleShimmer()

            Log.d("MyLog","getLaunchesUpcomingResult = ${it.results}")
            adapter.setListData(it.results)
        }
    }

    // startShimmerAnimation()
    private fun visibilityShimmer(){
        binding.shimmerContainer.startShimmerAnimation()
        binding.shimmerContainer.visibility = View.VISIBLE
        binding.rcView.visibility = View.GONE
    }

    // stopShimmerAnimation
    private fun invisibleShimmer(){
        binding.shimmerContainer.stopShimmerAnimation()
        binding.shimmerContainer.visibility = View.GONE
        binding.rcView.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerContainer.startShimmerAnimation()
    }

    override fun onPause() {
        binding.shimmerContainer.stopShimmerAnimation()
        super.onPause()
    }

    companion object {

        fun newInstance() = LaunchesUpcomingFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}