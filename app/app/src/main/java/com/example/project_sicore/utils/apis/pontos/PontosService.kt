package com.example.project_sicore.utils.apis.pontos

import com.example.project_sicore.utils.apis.crud.CrudInterface
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
import com.example.project_sicore.utils.modelos.pontos.Pontos
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
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
    suspend fun buscarPontos(lat: Double, lng: Double) : ArrayList<Pontos> {
        return api.buscarPontos(lat,lng)
    }

}