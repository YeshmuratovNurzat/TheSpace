package com.etoolkit.thespace.presentation.agencies

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
import com.etoolkit.thespace.databinding.FragmentAgenciesBinding

class AgenciesFragment : Fragment() {

    private var _binding : FragmentAgenciesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<AgenciesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAgenciesBinding.inflate(layoutInflater,container,false)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapterAgencies = AgenciesAdapter()
        binding.rcView.adapter = adapterAgencies

        adapterAgencies.onClick = {
            val bundle = Bundle()
            bundle.putSerializable("agencies",it)
            navController.navigate(R.id.action_agenciesFragment_to_agenciesDetailFragment, bundle)
        }

        viewModel.getAgencies()

        viewModel.getAgenciesResult.observe(viewLifecycleOwner){

            invisibleShimmer()

            Log.d("MyLog","getAgenciesResult = ${it.results}")
            adapterAgencies.setListData(it.results)
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
        super.onPause()
        binding.shimmerContainer.stopShimmerAnimation()
    }

    companion object {

        fun newInstance() = AgenciesFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}