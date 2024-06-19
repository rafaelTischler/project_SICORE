package com.example.project_sicore.activity.carregamento

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

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
        pegarLocalizacao()
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    pegarLocalizacao()
                } else {
                    // Permissão negada, enviar localização como 0,0
                    carregarMapa(0.0, 0.0)
                }
                return
            }

            else -> {
                // Outros códigos de erro
            }
        }
    }

    private fun pegarLocalizacao() {
        val localizacaoPrecisaCondicao = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        val localizacaoAproximadaCondicao = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        if (localizacaoPrecisaCondicao || localizacaoAproximadaCondicao) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            fusedLocationClient.lastLocation
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful && task.result != null) {
                        val location = task.result
                        val localizacaUsuario = LatLng(location.latitude, location.longitude)
                        carregarMapa(location.latitude, location.longitude)
                    } else {
                        // Tratamento de erro
                        Log.d("teste", "Erro ao obter localização")
                        carregarMapa(0.0, 0.0)
                    }
                }
        }
    }

    private fun carregarMapa(latitude: Double, longitude: Double) {
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashScreen, Mapa::class.java).apply {
                putExtra("latitude", latitude)
                putExtra("longitude", longitude)
            }
            startActivity(i)
            finish()
        }, 1500)
    }
}
