package com.example.project_sicore.utils.modelos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RecuperacaoContaRequest : Parcelable {
    var email: String = ""
    var codigo : String = ""
    var novaSenha : String = ""
}