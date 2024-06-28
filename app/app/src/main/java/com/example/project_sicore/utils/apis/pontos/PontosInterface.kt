package com.example.project_sicore.utils.apis.pontos

import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
import com.example.project_sicore.utils.modelos.pontos.Pontos
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PontosInterface {
    @POST("")
    suspend fun buscarPontos(@Body lat: Double, lng: Double) : ArrayList<Pontos>
}