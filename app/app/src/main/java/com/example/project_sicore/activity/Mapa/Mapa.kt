package com.example.project_sicore.activity.Mapa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.project_sicore.R
import com.example.project_sicore.utils.apis.pontos.PontosService
import com.example.project_sicore.utils.modelos.pontos.Pontos

import com.google.android.gms.maps.SupportMapFragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.launch


class Mapa : AppCompatActivity() {
    private lateinit var arrayPontos: ArrayList<Pontos>
    private var latitudeUsuario = -23.6814332
    private var longitudeUsuario = -46.9256264

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mapa)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapa_fragment) as SupportMapFragment
        latitudeUsuario = intent.getDoubleExtra("latitude", latitudeUsuario)
        longitudeUsuario = intent.getDoubleExtra("longitude", longitudeUsuario)

        mapFragment.getMapAsync { googleMap ->
            val camera = CameraPosition.Builder()
                .target(LatLng(latitudeUsuario,longitudeUsuario))
                .zoom(10f)
                .build()
            adicionarMarcadores(googleMap)
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(camera))

            googleMap.setOnMapLoadedCallback {

                googleMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(latitudeUsuario,longitudeUsuario))
                        .title("Usuario")
                )

            }
        }


    }

    private fun pegarPontosApi() {
//        var p = Pontos()
//        p.nome = "Ponto iffar"
//        p.latitude = -29.7016921
//        p.longitude = -54.6961514
//        p.descricao = " R. Vinte de Setembro, 2616 - São Vicente do Sul, RS, 97420-000"

        lifecycleScope.launch {
            val pontosApi = PontosService()
            arrayPontos = pontosApi.buscarPontos(latitudeUsuario, longitudeUsuario)
        }
//        arrayPontos = arrayListOf(
//            p
//        )
    }

    private fun adicionarMarcadores(googleMap: GoogleMap) {
        pegarPontosApi()
        for (ponto in arrayPontos) {
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(ponto.nome)
                    .snippet(ponto.descricao)
                    .position(LatLng(ponto.latitude,ponto.longitude))

            )
        }
    }
}
