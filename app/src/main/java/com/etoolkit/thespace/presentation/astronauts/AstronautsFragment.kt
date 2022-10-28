package com.etoolkit.thespace.presentation.astronauts

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentAstronautsBinding

class AstronautsFragment : Fragment() {

    private var _binding: FragmentAstronautsBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel by viewModels<AstronautsViewModel>()

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

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main)

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

        binding.employeeSearch.addTextChangedListener(object : TextWatcher{

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {

                if (p0?.length!! > 1){
                    viewModel.getAstronautSearch(binding.employeeSearch.text.toString())

                    visibilityShimmer()

                    viewModel.getAstronautsSearchResult.observe(viewLifecycleOwner){

                        if (it == null){
                            Log.d("MyLog","Search = null")
                            binding.searchResultNull.visibility = View.VISIBLE
                        }

                        invisibleShimmer()

                        binding.searchResultNull.visibility = View.GONE
                        Log.d("MyLog","getAstronautsSearchResult = ${it.results}")
                        astronautsAdapter.setListData(it.results.asReversed())
                    }
                }
            }
        })
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