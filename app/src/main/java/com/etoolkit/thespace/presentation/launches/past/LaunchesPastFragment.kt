package com.etoolkit.thespace.presentation.launches.past

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentLaunchesPastBinding
import com.etoolkit.thespace.presentation.launches.LaunchesViewModel

class LaunchesPastFragment : Fragment() {

    private var _binding : FragmentLaunchesPastBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController
    private val viewModel by viewModels<LaunchesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLaunchesPastBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val launchesPastAdapter = LaunchesPastAdapter()
        binding.rcView.adapter = launchesPastAdapter

        navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content)

        launchesPastAdapter.onClick = {
            val bundle = Bundle()
            bundle.putSerializable("launchPast",it)
            navController.navigate(R.id.action_launchesMainFragment_to_launchesPastDetailFragment, bundle)
        }

        viewModel.getLaunches()

        viewModel.getLaunchesResult.observe(viewLifecycleOwner){

            invisibleShimmer()

            Log.d("MyLog","getLaunchesResult = ${it.results}")
            launchesPastAdapter.setListData(it.results)
        }

        binding.search.setOnEditorActionListener { textView, i, keyEvent ->
            if(i == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getLaunchesSearch(binding.search.text.toString())
                visibilityShimmer()

                viewModel.getLaunchesSearchResult.observe(viewLifecycleOwner){
                    invisibleShimmer()
                    launchesPastAdapter.setListData(it.results.asReversed())
                }
            }
            true
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = LaunchesPastFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}