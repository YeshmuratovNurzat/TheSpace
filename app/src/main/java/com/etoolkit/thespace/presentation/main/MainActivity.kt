package com.etoolkit.thespace.presentation.main

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ActivityMainBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum

class MainActivity : AppCompatActivity(), DrawerViewInterface {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navigationView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.launches -> {
                    Toast.makeText(this,"Launches",Toast.LENGTH_SHORT).show()
                }

                R.id.events -> {
                    Toast.makeText(this,"Events",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_launchesMainFragment_to_eventsFragment)
                }

                R.id.astronauts -> {
                    Toast.makeText(this,"Astronauts",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_launchesMainFragment_to_astronautsFragment)
                }

                R.id.agencies -> {
                    Toast.makeText(this,"Agencies",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.action_launchesMainFragment_to_agenciesFragment)
                }

                R.id.exit ->{

                    val dialog = AlertDialog.Builder(this, R.style.DialogStyle)
                        .setIcon(R.drawable.ic_astronaut_exit)
                        .setTitle("Exit App")
                        .setMessage("Are you sure you want to close this activity?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",DialogInterface.OnClickListener { dialogInterface, i ->
                            finish()
                        })
                        .setNegativeButton("No",null)
                        .create()

                    dialog.show()

                }

            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        noInternetDialogPendulum()
    }

    private fun noInternetDialogPendulum(){

        NoInternetDialogPendulum.Builder(this, lifecycle).apply {
            dialogProperties.apply {
                connectionCallback = object : ConnectionCallback {
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {

                    }
                }

                cancelable = false
                noInternetConnectionTitle = "No Internet"
                noInternetConnectionMessage = "Check your Internet connection and try again."
                showInternetOnButtons = true
                pleaseTurnOnText = "Please turn on"
                wifiOnButtonText = "Wifi"
                mobileDataOnButtonText = "Mobile Data"

                onAirplaneModeTitle = "No Internet"
                onAirplaneModeMessage = "You have turned on the airplane mode."
                pleaseTurnOffText = "Please turn off"
                airplaneModeOffButtonText = "Airplane mode"
                showAirplaneModeOffButtons = true

            }
        }.build()
    }

    override fun closeDrawer() {
        binding.drawerLayout.close()
    }

    override fun showDrawer() {
        binding.drawerLayout.open()
    }

    override fun showBackIcon() {}

    override fun showHamburgerIcon() {}

}