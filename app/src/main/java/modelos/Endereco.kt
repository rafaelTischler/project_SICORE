import java.io.Serializable

class Endereco : Serializable {
    var cep: Int = 0
    var pais: String = ""
    var estado: String = ""
    var cidade: String = ""
    var bairro: String = ""
    var rua: String = ""
    var numero: Int = 0
}
