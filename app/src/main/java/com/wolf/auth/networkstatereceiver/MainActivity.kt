package com.wolf.auth.networkstatereceiver

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar

class MainActivity : AppCompatActivity(), NetworkStateReceiver.NetworkStateReceiverListener {

    private var networkStateReceiver: NetworkStateReceiver? = null
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        snackbar = Snackbar.make(findViewById(R.id.main_activity), R.string.no_internet_connection, Snackbar.LENGTH_INDEFINITE)
        setNetworkStateReceiver()
    }

    //function that sets the network state receiver to the activity
    private fun setNetworkStateReceiver(){
        networkStateReceiver = NetworkStateReceiver(this)
        networkStateReceiver!!.addListener(this)
        applicationContext.registerReceiver(networkStateReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

    }

    //function invoked when connected to the Internet
    override fun onNetworkAvailable() {
        snackbar!!.dismiss()

    }

    //funtion invoked when disconnected from the Internet
    override fun onNetworkUnavailable() {
        snackbar!!.show()
    }


}
