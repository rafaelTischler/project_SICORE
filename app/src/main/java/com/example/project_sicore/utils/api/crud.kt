package com.example.sicore

import Endereco
import android.util.Log
import com.example.project_sicore.activity.temp.TELAMOSTRARRESULTADO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import Usuario

class crud {

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://192.167.252.160/PHP/")
        .build()
        .create(Conta::class.java)

    val ma = TELAMOSTRARRESULTADO()

    fun CriaUsuario(u: Usuario) {
        retrofit.criar(
            u.cpf,
            u.nome,
            u.senha,
            u.confSenha,
            u.email,
            u.confEmail,
            u.tipo,
            u.dataNasci,
            u.numPhone,

            u.endereco.rua,
            u.endereco.numero,
            u.endereco.cidade,
            u.endereco.estado,
            u.endereco.pais,
            u.endereco.cep
        )
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("teste ", "Error: " + t.stackTraceToString())
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Log.d("teste", response.body()!!.cpf)
                        response.body()?.let {
                            if (response.body()!!.cpf.equals("vazio")) {
                                Log.d("teste", "Não foi possivel criar!")
                            } else {
                                Log.d("teste", "Criado com sucesso!")
                            }
                        }
                    }
                }
            }
            )
    }

    fun recebeUsuario(u: Usuario) {
        retrofit.logar(u.cpf, u.senha)
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("teste ", "Error: " + t.stackTraceToString())
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Log.d("teste", response.body()!!.cpf.toString())
                        response.body()?.let {
                            if (response.body()!!.cpf.equals("vazio")) {
                                Log.d("teste", "não foi possivel logar!")
                            } else {
                                Log.d("teste", "Logou com sucesso!")
                            }
                        }
                    }
                }
            }
            )
    }

    fun editaUsuario(u: Usuario) {
        retrofit.editar(u.cpf, u.senha, u.senha)
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("teste ", "Error: " + t.stackTraceToString())
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Log.d("teste", response.body()!!.senha)
                        response.body()?.let {
                            if (response.body()!!.senha.equals("vazio")) {
                                Log.d("teste", "Não foi possivel alterar!")
                            } else {
                                Log.d("teste", "Alterado com sucesso!")
                            }
                        }
                    }
                }
            }
            )
    }

    fun deletaUsuario(u: Usuario) {
        retrofit.delete(u.cpf, u.senha)
            .enqueue(object : Callback<Usuario> {

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Log.d("teste ", "Error: " + t.stackTraceToString())
                }

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        Log.d("teste", response.body()!!.cpf)
                        response.body()?.let {
                            if (response.body()!!.cpf.equals("vazio")) {
                                Log.d("teste", "Não foi possivel deletar a conta!")
                            } else {
                                Log.d("teste", "Deletado com sucesso!")
                            }
                        }
                    }
                }
            }
            )
    }

    interface Conta {
        @FormUrlEncoded
        @POST("Create/controller.php")
        fun criar(
            @Field("cpf") cpf: String,
            @Field("nome") nome: String,
            @Field("senha") senha: String,
            @Field("confSenha") confSenha: String,
            @Field("email") email: String,
            @Field("confEmail") confEmail: String,
            @Field("tipo") tipo: String,
            @Field("dataNasc") dataNasc: String,
            @Field("numPhone") numPhone: String,
            @Field("rua") rua: String,
            @Field("numero") numero: String,
            @Field("cidade") cidade: String,
            @Field("estado") estado: String,
            @Field("pais") pais: String,
            @Field("cep") cep: String



            ): Call<Usuario>

        @FormUrlEncoded
        @POST("read.php")
        fun logar(
            @Field("cpf") cpf: String,
            @Field("senha") senha: String
        ): Call<Usuario>

        @FormUrlEncoded
        @POST("update.php")
        fun editar(
            @Field("cpf") cpf: String,
            @Field("senha") senha: String,
            @Field("novasenha") novasenha: String,
        ): Call<Usuario>

        @FormUrlEncoded
        @POST("delete.php")
        fun delete(
            @Field("cpf") cpf: String,
            @Field("senha") senha: String,
        ): Call<Usuario>
    }
}