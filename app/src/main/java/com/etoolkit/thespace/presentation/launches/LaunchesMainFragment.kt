package com.etoolkit.thespace.presentation.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.etoolkit.thespace.R

class LaunchesMainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_launches_main, container, false)
    }

    companion object {

        fun newInstance() = LaunchesMainFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}