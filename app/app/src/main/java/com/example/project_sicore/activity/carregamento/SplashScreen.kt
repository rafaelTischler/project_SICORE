package com.example.project_sicore.activity.carregamento

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R
import com.example.project_sicore.activity.Mapa.Mapa
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val PERMISSION_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if (!isWiFiEnabled()){
            Toast.makeText(this, "Por favor, ative o Wi-Fi.", Toast.LENGTH_LONG).show()
            this.finish()
        }
        if (!isLocationEnabled()){
            Toast.makeText(this, "Por favor, ative o GPS", Toast.LENGTH_LONG).show()
        }else{
            if (!checkPermission()) requestPermission()
        }

    }
    // Função que é chamada automaticamente pelo android quando o usuario interage com a requisicao de localizacao.
    // O "@SuppressLint("MissingPermission")" serve para que o fusedLocationClient permita ser executado sem o pedido de localizacap (Ja estamos pedindo antes)
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_ID){
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY,null)
                        .addOnSuccessListener { location: Location ->
                            makeOtherAktivity(location.latitude, location.longitude)
                        }
            }
        }
    }

    private fun makeOtherAktivity(latitude: Double, longitude: Double){
        val i = Intent(this@SplashScreen, Mapa::class.java).apply {
            putExtra("latitude", latitude)
            putExtra("longitude", longitude)
        }
        startActivity(i)
        finish()
    }

    private fun isLocationEnabled(): Boolean{
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun isWiFiEnabled(): Boolean{
        val wifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    private fun checkPermission() : Boolean{
        return ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(){
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID)
    }
}
