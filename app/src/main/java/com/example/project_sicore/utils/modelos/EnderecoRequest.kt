package com.example.project_sicore.utils.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
class EnderecoRequest : Parcelable {
    var cep: String = ""
    var pais: String = ""
    var estado: String = ""
    var cidade: String = ""
    var bairro: String = ""
    var rua: String = ""
    var numero: String = ""
}