package com.example.project_sicore.utils.apis.pontos

import com.example.project_sicore.activity.Mapa.Mapa
import com.google.android.gms.maps.model.LatLng
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PontosService {
    private val baseEndpoint = "192.167.252.160"

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // pra log
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseEndpoint)
        .build()

    private val api = retrofit.create(PontosInterface::class.java)


    suspend fun pegarPontosColeta(latLong: LatLng) : ArrayList<Mapa.Pontos>{
        return api.pegarPontosColeta(latLong)
    }
}