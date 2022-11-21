package com.etoolkit.thespace.presentation.launches

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.etoolkit.thespace.presentation.launches.past.LaunchesPastFragment
import com.etoolkit.thespace.presentation.launches.upcoming.LaunchesUpcomingFragment

private const val NUM_TABS = 2

class ViewPagerAdapter(fragmentManager : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return LaunchesUpcomingFragment()
            1 -> return LaunchesPastFragment()
        }
        return LaunchesUpcomingFragment()
    }

}