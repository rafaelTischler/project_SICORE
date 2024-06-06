import java.io.Serializable

class Usuario (
    var nome: String = "",
    var cpf: String = "",
    var dataNasci: String = "",
    var telefone: String = "",
    var email: String = "",
    var senha: String = "",
    var endereco: Endereco = Endereco()
) : Serializable
