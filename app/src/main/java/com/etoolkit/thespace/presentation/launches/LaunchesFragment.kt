package com.etoolkit.thespace.presentation.launches

import android.content.Context
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
import com.etoolkit.thespace.databinding.FragmentLaunchesBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface

class LaunchesFragment : Fragment() {

    private var _binding : FragmentLaunchesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController : NavController
    private lateinit var drawerViewInterface: DrawerViewInterface
    private val viewModel by viewModels<LaunchesViewModel>()

    override fun onAttach(context: Context) {
        drawerViewInterface = requireActivity() as DrawerViewInterface
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLaunchesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val launchesAdapter = LaunchesAdapter()
        binding.rcView.adapter = launchesAdapter

        navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_content)

        launchesAdapter.onClick = {
            val bundle = Bundle()
            bundle.putSerializable("launch",it)
            navController.navigate(R.id.action_launchesFragment_to_launchesDetailFragment, bundle)
        }

        viewModel.getLaunches()

        viewModel.getLaunchesResult.observe(viewLifecycleOwner){

            invisibleShimmer()

            Log.d("MyLog","getLaunchesResult = ${it.results}")
            launchesAdapter.setListData(it.results)
        }

        binding.menu.setOnClickListener {
            drawerViewInterface.showDrawer()
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

        fun newInstance() = LaunchesFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}