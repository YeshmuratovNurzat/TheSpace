package com.etoolkit.thespace.presentation.astronauts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentAstronautsBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface

class AstronautsFragment : Fragment() {

    private var _binding: FragmentAstronautsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var drawerViewInterface: DrawerViewInterface
    private val viewModel by viewModels<AstronautsViewModel>()

    override fun onAttach(context: Context) {
        drawerViewInterface = requireActivity() as DrawerViewInterface
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAstronautsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content)

        val astronautsAdapter = AstronautsAdapter()
        binding.rcView.adapter = astronautsAdapter

        astronautsAdapter.onClick = {
            val bundle = Bundle()
            bundle.putSerializable("astronaut",it)
            navController.navigate(R.id.action_astronautsFragment_to_astronautsDetailFragment, bundle)
        }

        viewModel.getAstronauts()

        viewModel.getAstronautsResult.observe(viewLifecycleOwner) {

            invisibleShimmer()

            Log.d("MyLog", "getAstronautsResult = ${it.results}")
            astronautsAdapter.setListData(it.results)

        }

        //search astronaut
        binding.search.setOnEditorActionListener { textView, i, keyEvent ->

            if(i == EditorInfo.IME_ACTION_SEARCH){
                viewModel.getAstronautSearch(binding.search.text.toString())
                visibilityShimmer()

                viewModel.getAstronautsSearchResult.observe(viewLifecycleOwner){

                    invisibleShimmer()
                    Log.d("MyLog", "getAstronautsSearchResult = ${it.results}")
                    astronautsAdapter.setListData(it.results.asReversed())

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() = AstronautsFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}