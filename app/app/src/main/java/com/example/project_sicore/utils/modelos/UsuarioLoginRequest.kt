package com.example.project_sicore.utils.modelos

class UsuarioLoginRequest {
    var email: String = ""
    var senha: String = ""
    constructor(email: String, senha: String) {
        this.email = email
        this.senha = senha
    }
}