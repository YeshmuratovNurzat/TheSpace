package com.etoolkit.thespace.presentation.launches

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.etoolkit.thespace.databinding.FragmentLaunchesMainBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface
import com.google.android.material.tabs.TabLayoutMediator

class LaunchesMainFragment : Fragment() {

    private lateinit var binding: FragmentLaunchesMainBinding
    private lateinit var drawerViewInterface: DrawerViewInterface

    private val titles = arrayOf(
        "Upcoming",
        "Past"
    )

    override fun onAttach(context: Context) {
        drawerViewInterface = requireActivity() as DrawerViewInterface
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLaunchesMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapterViewPager = ViewPagerAdapter(parentFragmentManager, lifecycle)
        viewPager.adapter = adapterViewPager

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        binding.menu.setOnClickListener {
            drawerViewInterface.showDrawer()
        }
    }

    companion object {

        fun newInstance() = LaunchesMainFragment().apply {
            arguments = Bundle().apply {}
        }
    }

}