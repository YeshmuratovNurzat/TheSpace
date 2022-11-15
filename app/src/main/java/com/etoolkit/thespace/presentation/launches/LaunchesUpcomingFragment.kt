package com.etoolkit.thespace.presentation.launches

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.FragmentLaunchesUpcomingBinding
import java.text.SimpleDateFormat
import java.util.*

class LaunchesUpcomingFragment : Fragment() {

    private var _binding : FragmentLaunchesUpcomingBinding? = null
    private val binding get() = _binding!!
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

        viewModel.getLaunchesUpcoming()

        viewModel.getLaunchesUpcomingResult.observe(viewLifecycleOwner){
            Log.d("MyLog","getLaunchesUpcomingResult = ${it.results}")
            adapter.setListData(it.results)
        }
    }


    companion object {

        fun newInstance() = LaunchesUpcomingFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}