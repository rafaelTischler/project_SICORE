package com.example.project_sicore.utils.apis.crud

import com.example.project_sicore.utils.modelos.RecuperacaoContaRequest
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
import com.example.project_sicore.utils.modelos.UsuarioLoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CrudInterface {
    object CrudEndpoints{
        const val usuarioCadastroEndpoint = "Create/controller.php"
        const val usuarioLoginEndpoint = ""

        const val emailVerificacaoEndpoint = ""
        const val codigoVerificacaoEndpoint = ""
        const val atualizacaoSenhaEndpoint = ""

        const val usuarioEditarInformacoesEndpoint = ""
        const val usuarioPegarInformacoesEndpoint = ""
        const val viaCepBaseEndpoint = ""
        const val usuarioDeletarEndpoint = ""
    }
    @POST(CrudEndpoints.usuarioCadastroEndpoint)
    suspend fun cadastrarUsuario(@Body usuario: UsuarioCadastroRequest) : Response<Any>
    @POST(CrudEndpoints.usuarioLoginEndpoint)
    suspend fun logarUsuario(@Body usuario: UsuarioLoginRequest) : Response<Any>
    @POST(CrudEndpoints.emailVerificacaoEndpoint)
    suspend fun verificarEmail(@Body email: String) : Response<Any>
    @POST(CrudEndpoints.codigoVerificacaoEndpoint)
    suspend fun verificaCodigo(@Body usuario: RecuperacaoContaRequest) : Response<Any>
    @POST(CrudEndpoints.atualizacaoSenhaEndpoint)
    suspend fun atualizaSenha(@Body usuario: RecuperacaoContaRequest) : Response<Any>
}
