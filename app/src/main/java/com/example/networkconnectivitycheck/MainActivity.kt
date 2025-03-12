package com.example.networkconnectivitycheck


import android.annotation.SuppressLint
import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.networkconnectivitycheck.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rootView: View

    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()
        rootView = findViewById(android.R.id.content)



    }
    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.ANIMATION_MODE_SLIDE)
        snackbar.show()
    }

    @SuppressLint("SetTextI18n")
    private fun callNetworkConnection() {
        checkNetworkConnection = CheckNetworkConnection(application)
        checkNetworkConnection.observe(this) { state ->
            when (state) {
                NetworkState.CONNECTED -> {
                    binding.imageView.setImageResource(R.drawable.wifi_connect)
                    binding.textView.setText("Network Connected")
                    binding.textView.setTextColor(Color.parseColor("#4CAF50"))
                    showSnackbar("Network Connected")
                }
                NetworkState.DISCONNECTED -> {
                    binding.imageView.setImageResource(R.drawable.wifi_disconnected)
                    binding.textView.setText("Network Disconnected")
                    binding.textView.setTextColor(Color.parseColor("#F44336"))
                    showSnackbar("No Internet Connection")
                }
                NetworkState.NO_INTERNET-> {
                    binding.imageView.setImageResource(R.drawable.wifi_alert)
                    binding.textView.setText("Wi-fi Connected But No Intenet Access")
                    binding.textView.setTextColor(Color.parseColor("#FFDE4211"))
                    showSnackbar("Wi-fi Connected But No Intenet Access")

                }
            }
        }
    }

    }
