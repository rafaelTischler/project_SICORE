package com.example.project_sicore.utils.apis.viaCep

import com.example.project_sicore.utils.modelos.EnderecoViaCepResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepInterface {
    @GET("{cep}/json/")
    suspend fun buscaCep(@Path("cep")cep:String) : Response<EnderecoViaCepResponse>
}
