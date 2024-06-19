package com.example.project_sicore.utils.apis.pontos

import com.example.project_sicore.activity.Mapa.Mapa
import com.google.android.gms.maps.model.LatLng
import retrofit2.http.Body
import retrofit2.http.POST

interface PontosInterface {
    @POST("PontoColeta/ReturnPoint.php")
    suspend fun pegarPontosColeta(@Body latLong: LatLng) : ArrayList<Mapa.Pontos>
}