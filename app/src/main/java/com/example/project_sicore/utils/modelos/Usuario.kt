import java.io.Serializable

class Usuario (
    var tipo: String = "",
    var nome: String = "",
    var cpf: String = "",
    var dataNasci: String = "",
    var telefone: String = "",
    var email: String = "",
    var confEmail: String = "",
    var senha: String = "",
    var confSenha: String = "",
    var numPhone: String = "",
    var endereco: Endereco = Endereco()
) : Serializable
