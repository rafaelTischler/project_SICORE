package com.example.project_sicore.utils.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class UsuarioCadastroRequest(
    var tipo: String = "",
    var nome: String = "",
    var cpf: String = "35645645",
    var dataNasci: String = "",
    var telefone: String = "",
    var email: String = "",
    var senha: String = "",
    var endereco: EnderecoRequest = EnderecoRequest()
) : Parcelable {
    constructor(
        tipo: String = "",
        nome: String = "",
        cpf: String = "",
        dataNasci: String = "",
        telefone: String = "",
        email: String = "",
        senha: String = ""
    ) : this(tipo, nome, cpf, dataNasci, telefone, email, senha, EnderecoRequest())
}
