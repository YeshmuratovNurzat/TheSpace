package com.etoolkit.thespace.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.etoolkit.thespace.R
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

}