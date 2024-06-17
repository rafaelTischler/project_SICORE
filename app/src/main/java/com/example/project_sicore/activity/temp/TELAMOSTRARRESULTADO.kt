package com.example.project_sicore.activity.temp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R
import android.widget.TextView
import com.example.project_sicore.utils.modelos.UsuarioCadastroRequest
//import com.example.sicore.crud
import org.json.JSONObject

class TELAMOSTRARRESULTADO : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_telamostrarresultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var usuario = intent.getSerializableExtra("usuario") as UsuarioCadastroRequest

        // Pedi pro gpt faze pra mim, colocar bib Gson()
        val jsonObject = JSONObject()
        jsonObject.put("nome", usuario.nome)
        jsonObject.put("cpf", usuario.cpf)
        jsonObject.put("dataNasci", usuario.dataNasci)
        jsonObject.put("telefone", usuario.telefone)
        jsonObject.put("email", usuario.email)
        jsonObject.put("senha", usuario.senha)

        // Convertendo o objeto Endereco para JSON
        val enderecoJsonObject = JSONObject()
        enderecoJsonObject.put("cep", usuario.endereco.cep)
        enderecoJsonObject.put("pais", usuario.endereco.pais)
        enderecoJsonObject.put("estado", usuario.endereco.estado)
        enderecoJsonObject.put("cidade", usuario.endereco.cidade)
        enderecoJsonObject.put("bairro", usuario.endereco.bairro)
        enderecoJsonObject.put("rua", usuario.endereco.rua)
        enderecoJsonObject.put("numero", usuario.endereco.numero)

        jsonObject.put("endereco", enderecoJsonObject)

        // Convertendo o JSON de volta para uma string
        val jsonAsString = jsonObject.toString()


        var textViewJson: TextView = findViewById(R.id.resJson)
        textViewJson.text = jsonAsString

    }
}