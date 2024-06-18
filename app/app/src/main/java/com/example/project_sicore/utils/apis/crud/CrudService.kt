package com.example.project_sicore.utils.apis.crud

import com.example.project_sicore.utils.modelos.RecuperacaoContaRequest
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
import com.example.project_sicore.utils.modelos.UsuarioLoginRequest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response

class CrudService {
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

    private val api = retrofit.create(CrudInterface::class.java)
    suspend fun cadastrarUsuario(usuario: UsuarioCadastroRequest) : Response<Any> {
        return api.cadastrarUsuario(usuario)
    }
    suspend fun logarUsuario(usuario: UsuarioLoginRequest) : Response<Any>{
        return api.logarUsuario(usuario)
    }

    suspend fun verificarEmail(email : String) : Response<Any>{
        return api.verificarEmail(email)
    }
    suspend fun verificaCodigo(usuario: RecuperacaoContaRequest) : Response<Any>{
        return api.verificaCodigo(usuario)
    }
    suspend fun atualizaSenha(usuario: RecuperacaoContaRequest) : Response<Any>{
        return api.atualizaSenha(usuario)
    }
}