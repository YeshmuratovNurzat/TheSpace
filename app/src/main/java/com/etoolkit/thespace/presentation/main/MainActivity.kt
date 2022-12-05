package com.etoolkit.thespace.presentation.main

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.etoolkit.thespace.R
import com.etoolkit.thespace.databinding.ActivityMainBinding
import com.etoolkit.thespace.util.interfaces.DrawerViewInterface
import com.google.android.material.navigation.NavigationView
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum

class MainActivity : AppCompatActivity(), DrawerViewInterface {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.hide()

        val drawerLayout = binding.drawerLayout
        val navigationView : NavigationView = binding.navigationView

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.launches,
            R.id.events,
            R.id.agencies,
            R.id.astronauts),
            drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navigationView, navController)

        navigationView.setNavigationItemSelectedListener {

            when(it.itemId){

                R.id.launches -> {
                    Toast.makeText(this,"Launches",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.launchesMainFragment)
                }

                R.id.events -> {
                    Toast.makeText(this,"Events",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.eventsFragment)
                }

                R.id.astronauts -> {
                    Toast.makeText(this,"Astronauts",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.astronautsFragment)
                }

                R.id.agencies -> {
                    Toast.makeText(this,"Agencies",Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.agenciesFragment)
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

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}