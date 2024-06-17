package com.example.project_sicore.utils.apis.viaCep

import com.example.project_sicore.utils.modelos.EnderecoViaCepResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ViaCepService {
    private val baseEndpoint = "https://viacep.com.br/ws/"

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

    private val api = retrofit.create(ViaCepInterface::class.java)
    suspend fun buscaCep(cep:String) : retrofit2.Response<EnderecoViaCepResponse> {
        return api.buscaCep(cep)
    }
}