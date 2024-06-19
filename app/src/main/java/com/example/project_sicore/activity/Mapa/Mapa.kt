package com.example.project_sicore.activity.Mapa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.SupportMapFragment

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener

class Mapa : AppCompatActivity() {
    data class Pontos(
        val nome: String,
        val latLng: LatLng,
        val descricao: String,
        val endereco: String,
    )

    private lateinit var arrayPontos: ArrayList<Pontos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mapa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        pegarPontosApi();
        val localizacaoUsuario = LatLng(intent.getDoubleExtra("latitude",-23.563987), intent.getDoubleExtra("longitude", -46.653620))

        val cameraPosition = CameraPosition.builder()
            .target(localizacaoUsuario)
            .zoom(10f)
            .build()

        val mapOption = GoogleMapOptions()
            .camera(cameraPosition)

        val mapFragment = SupportMapFragment.newInstance(mapOption)
        supportFragmentManager.beginTransaction().replace(R.id.mapa_fragment, mapFragment).commit()

        mapFragment.getMapAsync { googleMap ->
            adicionarMarcadores(googleMap)

            googleMap.setOnMapLoadedCallback {
//                val bauds = LatLngBounds.builder()
//                val zoomLevel = 15f // Ajuste o nível de zoom conforme necessário
//                val cameraUpdate = CameraUpdateFactory.newLatLngZoom(usuarioLocalizacao, zoomLevel)
//
//                googleMap.moveCamera(cameraUpdate)
//                googleMap.addMarker(
//                    MarkerOptions()
//                        .position(usuarioLocalizacao)
//                        .title("Usuario")
//                )

            }
        }


    }

    private fun pegarPontosApi() {
        arrayPontos = arrayListOf(
//            Pontos(
//                "Ponto iffar",
//                LatLng(-29.7018462, -54.6987538),
//                " R. Vinte de Setembro, 2616 - São Vicente do Sul, RS, 97420-000"
//            )
        )
    }

    private fun adicionarMarcadores(googleMap: GoogleMap) {
        for (ponto in arrayPontos) {
            val maeker = googleMap.addMarker(
                MarkerOptions()
                    .title(ponto.nome)
                    .snippet(ponto.endereco)
                    .position(ponto.latLng)

            )

        }


    }


}
