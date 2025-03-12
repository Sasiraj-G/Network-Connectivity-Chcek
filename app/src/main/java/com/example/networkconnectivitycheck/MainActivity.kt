package com.example.networkconnectivitycheck


import android.annotation.SuppressLint
import android.graphics.Color
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.networkconnectivitycheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var checkNetworkConnection: CheckNetworkConnection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        callNetworkConnection()



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
                }
                NetworkState.DISCONNECTED -> {
                    binding.imageView.setImageResource(R.drawable.wifi_disconnected)
                    binding.textView.setText("Network Disconnected")
                    binding.textView.setTextColor(Color.parseColor("#F44336"))

                }
                NetworkState.NO_INTERNET-> {
                    binding.imageView.setImageResource(R.drawable.wifi_alert)
                    binding.textView.setText("Network Connected But No Intenet Access")
                    binding.textView.setTextColor(Color.parseColor("#FFDE4211"))

                }
            }
        }
    }

    }
