package com.etoolkit.thespace.presentation.agencies

import android.content.Context
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
import com.etoolkit.thespace.databinding.FragmentAgenciesBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface

class AgenciesFragment : Fragment() {

    private var _binding : FragmentAgenciesBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var drawerViewInterface: DrawerViewInterface
    private val viewModel by viewModels<AgenciesViewModel>()

    override fun onAttach(context: Context) {
        drawerViewInterface = requireActivity() as DrawerViewInterface
        super.onAttach(context)
    }

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

        binding.search.setOnEditorActionListener { textView, i, keyEvent ->

             if(i == EditorInfo.IME_ACTION_SEARCH){
                 viewModel.getAgenciesSearch(binding.search.text.toString())
                 visibilityShimmer()

                 viewModel.getAgenciesSearchResult.observe(viewLifecycleOwner){
                     invisibleShimmer()
                     adapterAgencies.setListData(it.results.asReversed())
                 }
             }

            true
        }

        //showDrawer
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
        super.onPause()
        binding.shimmerContainer.stopShimmerAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        fun newInstance() = AgenciesFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}