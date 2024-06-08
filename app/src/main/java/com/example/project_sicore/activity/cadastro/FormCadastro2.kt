package com.example.project_sicore.activity.cadastro

import Usuario
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_sicore.R
import com.example.project_sicore.activity.temp.TELAMOSTRARRESULTADO

class FormCadastro2 : AppCompatActivity() {

    var usuario = Usuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_cadastro2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        usuario = intent.getSerializableExtra("usuario") as Usuario

        val btnVoltarEtapa2: Button = this.findViewById(R.id.btn_voltar)
        btnVoltarEtapa2.setOnClickListener {
            this.finish();
        }
        var btnCadastrar: Button = this.findViewById(R.id.btn_cadastrar)
        btnCadastrar.setOnClickListener(){
            val intent = Intent(this, TELAMOSTRARRESULTADO::class.java)
            pegarValoresInput()
            intent.putExtra("usuario",usuario)
            startActivity(intent)
        }
    }

    fun pegarValoresInput(){
        var EditTextPais = findViewById<EditText>(R.id.edit_pais)
        var EditTextCidade = findViewById<EditText>(R.id.edit_cidade)
        var EditTextEstado = findViewById<EditText>(R.id.edit_estado)
        var EditTextBairro = findViewById<EditText>(R.id.edit_bairro)
        var EditTextRua = findViewById<EditText>(R.id.edit_rua)

        usuario.endereco.pais = EditTextPais.text.toString()
        usuario.endereco.estado = EditTextEstado.text.toString()
        usuario.endereco.cidade = EditTextCidade.text.toString()
        usuario.endereco.bairro = EditTextBairro.text.toString()
        usuario.endereco.rua = EditTextRua.text.toString()

    }
}